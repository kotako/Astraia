package io.gitub.kotako.astraia.articles

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.databinding.FragmentArticlesBinding
import kotlinx.android.synthetic.main.fragment_articles.view.*

class ArticlesFragment : Fragment(), ArticleItemNavigator {

    private lateinit var articlesRecyclerAdapter: ArticlesRecyclerAdapter
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var binding: FragmentArticlesBinding

    companion object {
        fun newInstance(): Fragment = ArticlesFragment()
    }

    fun setViewModel(viewModel: ArticlesViewModel) {
        this.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_articles, container, false)
        binding = FragmentArticlesBinding.bind(view)
        binding.viewModel = viewModel

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
        // start detail activity
    }

    private fun setUpRecyclerView() {
        binding.articlesList.adapter = ArticlesRecyclerAdapter(viewModel.articles.value as List<Article>, this)
    }
}