package io.gitub.kotako.astraia.articles

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {
    lateinit var titleView: TextView
    lateinit var authorsView: TextView
}