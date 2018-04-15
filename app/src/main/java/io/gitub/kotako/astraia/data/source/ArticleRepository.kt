package io.gitub.kotako.astraia.data.source

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Query
import io.reactivex.Single
import javax.inject.Inject

open class ArticleRepository @Inject constructor(
        private val remoteDataSource: DataSource
) : DataSource {

    override fun fetchArticle(articleId: Long): Single<Article> {
        return remoteDataSource.fetchArticle(articleId = articleId)
    }

    override fun fetchArticles(q: Query): Single<List<Article>> {
        return remoteDataSource.fetchArticles(q)
    }

    override fun fetchAuthor(authorId: Long): Single<List<Author>> {
        return remoteDataSource.fetchAuthor(authorId = authorId)
    }

    override fun fetchAuthors(q: Query): Single<List<Author>> {
        return remoteDataSource.fetchAuthors(q)
    }

}