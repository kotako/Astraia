package io.gitub.kotako.astraia.articles

import android.support.v7.widget.RecyclerView
import android.view.View
import io.gitub.kotako.astraia.databinding.ArticleViewBinding

class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding: ArticleViewBinding = ArticleViewBinding.bind(view)
}