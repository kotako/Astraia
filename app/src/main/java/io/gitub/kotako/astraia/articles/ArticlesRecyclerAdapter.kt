package io.gitub.kotako.astraia.articles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.gitub.kotako.astraia.BR
import io.gitub.kotako.astraia.R
import io.gitub.kotako.astraia.data.Entity.Article
import javax.inject.Inject

class ArticlesRecyclerAdapter(
        private val articles: List<Article>,
        private val navigator: ArticleItemNavigator
): RecyclerView.Adapter<ArticleViewHolder>() {

    @Inject
    lateinit var viewModel: ArticleItemViewModel

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.binding.titleTextView.text = articles[position].title
        holder.binding.descriptionTextView.text = articles[position].description
        holder.binding.authorsTextView.text = articles[position].authors?.joinToString (
                separator = ", ", transform = { author -> author?.name ?: "Unknown" }
        )
        viewModel.setNavigator(navigator)
        holder.binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
            ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_view, parent, false))
}