package io.gitub.kotako.astraia.data.source

import io.gitub.kotako.astraia.data.Article
import io.gitub.kotako.astraia.data.ArticleColumn
import io.gitub.kotako.astraia.data.Author
import io.reactivex.Single

class ArticleRepository: DataSource {

    override fun fetchArticle(articleId: Long): Single<Article> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchArticles(keyword: String, count: Int?, lang: String?, startIndex: Int?, title: String?, author: String?, authorId: Long?, issn: String?, publisher: String?, affiliation: String?, journal: String?, volumeNumber: Int?, issueNumber: Int?, page: Int?, references: String?, yearFrom: Int?, yearTo: Int?, articleBodyAvailable: Int?, sortOrder: Int?): Single<List<ArticleColumn>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAuthor(authorId: Long): Single<Author> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAuthors(keyword: String, count: Int?, lang: String?, startIndex: Int?, sortOrder: Int?): Single<List<Author>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}