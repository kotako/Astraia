package io.gitub.kotako.astraia.articles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.data.Entity.Article

class ArticlesRecyclerAdapter(
        private val articles: List<Article>
): RecyclerView.Adapter<ArticleViewHolder>() {
    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.titleView.text = articles[position].title
        holder.descriptionView.text = articles[position].description
        holder.authorsView.text = articles[position].authors?.joinToString (
                separator = ", ", transform = { author -> author?.name ?: "Unknown" }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
            ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_view, parent, false))
}