package io.gitub.kotako.astraia.data.source.remote

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Query
import io.gitub.kotako.astraia.data.source.DataSource
import io.gitub.kotako.astraia.data.source.remote.api.CiniiApi
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

open class RemoteDataSource @Inject constructor(
        private val retrofit: Retrofit
) : DataSource {

    override fun fetchArticle(articleId: Long): Single<Article> {
        return retrofit.create(CiniiApi::class.java)
                .fetchArticle(articleId = articleId)
                .map { response -> response.body.first() as Article }
                .singleOrError()
    }

    override fun fetchArticles(q: Query): Single<List<Article>> {
        return retrofit.create(CiniiApi::class.java)
                .fetchArticles(
                        keyword = q.keyword,
                        count = q.count,
                        lang = q.lang,
                        startIndex = q.startIndex,
                        title = q.title,
                        author = q.author,
                        authorId = q.authorId,
                        issn = q.issn,
                        publisher = q.publisher,
                        affiliation = q.affilication,
                        journal = q.journal,
                        volumeNumber = null,
                        issueNumber = null,
                        page = q.page,
                        references = null,
                        yearFrom = q.yearFrom,
                        yearTo = q.yearTo,
                        articleBodyAvailable = q.articleBodyAvailable,
                        sort = q.sort)
                .map { response -> response.body.first().articles.map { article -> article as Article } }
                .singleOrError()
    }

    override fun fetchAuthor(authorId: Long): Single<List<Author>> {
        return retrofit.create(CiniiApi::class.java)
                .fetchAuthor(authorId = authorId)
                .map { response -> response.body.map { author -> author as Author } }
                .singleOrError()
    }

    override fun fetchAuthors(q: Query): Single<List<Author>> {
        return retrofit.create(CiniiApi::class.java)
                .fetchAuthors(
                        keyword = q.keyword,
                        count = q.count,
                        lang = q.lang,
                        startIndex = q.startIndex,
                        sort = q.sort)
                .map { response -> response.body.first().authors.map { author -> author as Author } }
                .singleOrError()
    }
}