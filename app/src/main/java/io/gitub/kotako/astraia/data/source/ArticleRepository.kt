package io.gitub.kotako.astraia.data.source

import io.gitub.kotako.astraia.data.Article
import io.gitub.kotako.astraia.data.ArticleColumn
import io.gitub.kotako.astraia.data.Author
import io.gitub.kotako.astraia.data.AuthorColumn
import io.reactivex.Single
import javax.inject.Inject

open class ArticleRepository @Inject constructor(
        private val remoteDataSource: DataSource
) : DataSource {

    override fun fetchArticle(articleId: Long): Single<Article> {
        return remoteDataSource.fetchArticle(articleId = articleId)
    }

    override fun fetchArticles(keyword: String, count: Int?, lang: String?, startIndex: Int?, title: String?, author: String?, authorId: Long?, issn: String?, publisher: String?, affiliation: String?, journal: String?, volumeNumber: Int?, issueNumber: Int?, page: Int?, references: String?, yearFrom: Int?, yearTo: Int?, articleBodyAvailable: Int?, sortOrder: Int?): Single<List<ArticleColumn>> {
        return remoteDataSource.fetchArticles(keyword = keyword, count = count, lang = lang, startIndex = startIndex, title = title, author = author, authorId = authorId, issn = issn, publisher = publisher, affiliation = affiliation, journal = journal, volumeNumber = volumeNumber, issueNumber = issueNumber, page = page, references = references, yearFrom = yearFrom, yearTo = yearTo, articleBodyAvailable = articleBodyAvailable, sortOrder = sortOrder)
    }

    override fun fetchAuthor(authorId: Long): Single<List<Author>> {
        return remoteDataSource.fetchAuthor(authorId = authorId)
    }

    override fun fetchAuthors(keyword: String, count: Int?, lang: String?, startIndex: Int?, sortOrder: Int?): Single<List<AuthorColumn>> {
        return remoteDataSource.fetchAuthors(keyword = keyword, count = count, lang = lang, startIndex = startIndex, sortOrder = sortOrder)
    }

}