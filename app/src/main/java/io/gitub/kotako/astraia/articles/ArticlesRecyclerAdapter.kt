package io.gitub.kotako.astraia.articles

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.gitub.kotako.astraia.data.Entity.Article

class ArticlesRecyclerAdapter(
        private val articles: List<Article>
): RecyclerView.Adapter<ArticleViewHolder>() {
    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // holder.authorsView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        // create view
        return ArticleViewHolder(null as View)
    }
}