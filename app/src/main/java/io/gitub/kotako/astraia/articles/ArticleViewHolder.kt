package io.gitub.kotako.astraia.articles

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.article_view.view.*

class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val titleView: TextView = view.titleTextView
    val authorsView: TextView = view.authorsTextView
    val descriptionView: TextView = view.descriptionTextView
}