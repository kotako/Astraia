package io.gitub.kotako.astraia.articles

import io.gitub.kotako.astraia.data.Entity.Article

interface ArticleItemNavigator {
    fun onStartArticleDetail(article: Article)
}