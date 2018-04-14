package io.gitub.kotako.astraia.data.source.remote

import io.gitub.kotako.astraia.data.Article
import io.gitub.kotako.astraia.data.ArticleColumn
import io.gitub.kotako.astraia.data.Author
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
                .map { response -> response.value.first() }
                .singleOrError()
    }

    override fun fetchArticles(keyword: String, count: Int?, lang: String?, startIndex: Int?, title: String?, author: String?, authorId: Long?, issn: String?, publisher: String?, affiliation: String?, journal: String?, volumeNumber: Int?, issueNumber: Int?, page: Int?, references: String?, yearFrom: Int?, yearTo: Int?, articleBodyAvailable: Int?, sortOrder: Int?): Single<List<ArticleColumn>> {
        return retrofit.create(CiniiApi::class.java)
                .fetchArticles(
                        keyword = "android",
                        count = count,
                        lang = lang,
                        startIndex = startIndex,
                        title = title,
                        author = author,
                        authorId = authorId,
                        issn = issn,
                        publisher = publisher,
                        affiliation = affiliation,
                        journal = journal,
                        volumeNumber = volumeNumber,
                        issueNumber = issueNumber,
                        page = page,
                        references = references,
                        yearFrom = yearFrom,
                        yearTo = yearTo,
                        articleBodyAvailable = articleBodyAvailable,
                        sort = sortOrder)
                .map { response -> response.value }
                .singleOrError()
    }

    override fun fetchAuthor(authorId: Long): Single<Author> {
        return retrofit.create(CiniiApi::class.java)
                .fetchAuthor(authorId = authorId)
                .map { response -> response.value.first() }
                .singleOrError()
    }

    override fun fetchAuthors(keyword: String, count: Int?, lang: String?, startIndex: Int?, sortOrder: Int?): Single<List<Author>> {
        return retrofit.create(CiniiApi::class.java)
                .fetchAuthors(
                        keyword = keyword,
                        count = count,
                        lang = lang,
                        startIndex = startIndex,
                        sort = sortOrder)
                .map { response -> response.value }
                .singleOrError()
    }
}