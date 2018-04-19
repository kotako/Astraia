package io.gitub.kotako.astraia.articles

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.gitub.kotako.astraia.databinding.ArticleViewBinding
import kotlinx.android.synthetic.main.article_view.view.*

class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding: ArticleViewBinding = ArticleViewBinding.bind(view)
}