package io.gitub.kotako.astraia.data.source

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Query
import io.reactivex.Single

interface DataSource {
    fun fetchArticles(q: Query): Single<List<Article>>

    fun fetchArticle(articleId: Long): Single<Article>

    fun fetchAuthors(q: Query): Single<List<Author>>

    fun fetchAuthor(authorId: Long): Single<List<Author>>
}