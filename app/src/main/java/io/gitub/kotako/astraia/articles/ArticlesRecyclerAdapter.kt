package io.gitub.kotako.astraia.articles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.ArticleRepository

class ArticlesRecyclerAdapter(
        var articles: List<Article>,
        private val navigator: ArticleItemNavigator,
        private val repository: ArticleRepository,
        private val onBottomReached: () -> Unit
): RecyclerView.Adapter<ArticleViewHolder>() {

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // load more articles
        if (position == articles.size - 1) onBottomReached()

        val viewModel = ArticleItemViewModel(repository)
        viewModel.setNavigator(navigator)
        holder.binding.viewModel = viewModel
        holder.binding.article = articles[position]
        holder.binding.authorsTextView.text = articles[position].authors?.joinToString (
                separator = ", ", transform = { author -> author?.name ?: "Unknown" }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
            ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_view, parent, false))
}