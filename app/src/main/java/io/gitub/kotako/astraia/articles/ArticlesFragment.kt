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
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.databinding.FragmentArticlesBinding
import io.gitub.kotako.astraia.di.ViewModelFactory
import java.lang.ref.WeakReference
import javax.inject.Inject

class ArticlesFragment : DaggerFragment(), ArticleItemNavigator {

    @Inject lateinit var repository: ArticleRepository
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var binding: FragmentArticlesBinding

    companion object {
        fun newInstance(): ArticlesFragment = ArticlesFragment()
    }

    fun setViewModel(viewModel: ArticlesViewModel) {
        this.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_articles, container, false)
        binding = FragmentArticlesBinding.bind(view)
        binding.viewModel = viewModel

        binding.viewModel?.articles?.observe(this, Observer<MutableList<Article>> { result ->
            (binding.articlesList.adapter as ArticlesRecyclerAdapter).articles = result as List<Article>
            binding.articlesList.adapter.notifyDataSetChanged()
        })
        binding.viewModel?.isLoading?.observe(this, Observer {})

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }

    override fun onStartArticleDetail() {
        // TODO : implement method
        // start detail activity
    }

    private fun setUpRecyclerView() {
        binding.articlesList.adapter = ArticlesRecyclerAdapter(viewModel.articles.value as List<Article>, WeakReference(this), repository)
        binding.articlesList.layoutManager = LinearLayoutManager(activity)
        binding.articlesList.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
    }
}