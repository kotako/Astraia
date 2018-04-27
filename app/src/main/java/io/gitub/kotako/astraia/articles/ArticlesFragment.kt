package io.gitub.kotako.astraia.articles

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.databinding.FragmentArticlesBinding
import io.gitub.kotako.astraia.detail.ArticleDetailActivity
import javax.inject.Inject

class ArticlesFragment : DaggerFragment(), ArticleItemNavigator {

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

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }

    override fun onStartArticleDetail(article: Article) {
        if (activity == null) return
        ArticleDetailActivity.start(activity!!, article)
    }

    private fun setUpRefreshView() {
        binding.refreshLayout.setOnRefreshListener {
            viewModel.articles.value = mutableListOf()
            viewModel.fetchArticles()
        }
    }

    private fun setUpRecyclerView() {
        binding.articlesList.apply {
            adapter = ArticlesRecyclerAdapter(
                    articles = viewModel.articles.value?.toMutableList() ?: mutableListOf(),
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