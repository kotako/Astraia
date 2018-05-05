package io.gitub.kotako.astraia.articles

import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import dagger.android.support.DaggerFragment
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.data.source.Query
import io.gitub.kotako.astraia.databinding.FragmentArticlesBinding
import io.gitub.kotako.astraia.detail.ArticleDetailActivity
import io.gitub.kotako.astraia.di.ViewModelFactory
import javax.inject.Inject

class ArticlesFragment : DaggerFragment(), ArticleItemNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var repository: ArticleRepository
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var binding: FragmentArticlesBinding

    private val articlesListObserver = Observer<MutableList<Article>> {
        it?.let {
            val prevSize = (binding.articlesList.adapter as ArticlesRecyclerAdapter).articles.size
            (binding.articlesList.adapter as ArticlesRecyclerAdapter).articles = it

            if (it.size > prevSize) {
                binding.articlesList.adapter.notifyItemRangeInserted(prevSize, it.size)
            } else {
                binding.articlesList.adapter.notifyDataSetChanged()
            }
        }
    }

    private val loadObserver = Observer<Boolean> {
        it?.let {
            binding.refreshLayout.isRefreshing = it
        }
    }

    companion object {
        fun newInstance(): ArticlesFragment = ArticlesFragment()
    }

    fun setViewModel(viewModel: ArticlesViewModel) {
        this.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.articles.observeForever(articlesListObserver)
        viewModel.isLoading.observeForever(loadObserver)
        binding.viewModel = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
        setUpRefreshView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search, menu)
        val view = (menu?.findItem(R.id.search) as MenuItem).actionView as SearchView
        val searchService = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        view.setSearchableInfo(searchService.getSearchableInfo(activity?.componentName))
        view.setIconifiedByDefault(true)
        view.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                view.clearFocus()
                viewModel.searchArticles(query ?: "")
                return true
            }
        })
    }

    override fun onStartArticleDetail(article: Article) {
        if (activity == null) return
        ArticleDetailActivity.start(activity!!, article)
    }

    private fun setUpRefreshView() {
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshArticles()
        }
    }

    private fun setUpRecyclerView() {
        binding.articlesList.apply {
            adapter = ArticlesRecyclerAdapter(
                    articles = mutableListOf(),
                    navigator = this@ArticlesFragment,
                    repository = repository,
                    onBottomReached = ::onBottomReached)
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        }
    }

    private fun onBottomReached() {
        viewModel.fetchArticles()
    }
}