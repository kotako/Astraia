package io.gitub.kotako.astraia.articles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.ArticleRepository
import java.lang.ref.WeakReference

class ArticlesRecyclerAdapter(
        var articles: List<Article>,
        private val navigator: WeakReference<ArticleItemNavigator>,
        private val repository: ArticleRepository
): RecyclerView.Adapter<ArticleViewHolder>() {

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val viewModel = ArticleItemViewModel(repository)
        if (navigator.get() != null) viewModel.setNavigator(navigator.get()!!)
        holder.binding.viewModel = viewModel
        holder.binding.article = articles[position]
        holder.binding.authorsTextView.text = articles[position].authors?.joinToString (
                separator = ", ", transform = { author -> author?.name ?: "Unknown" }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
            ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_view, parent, false))
}