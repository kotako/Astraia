package io.gitub.kotako.astraia.data.source

import io.gitub.kotako.astraia.data.*
import io.reactivex.Single

interface DataSource {
    fun fetchArticles(keyword: String,
                      count: Int? = null,
                      lang: String? = null,
                      startIndex: Int? = null,
                      title: String? = null,
                      author: String? = null,
                      authorId: Long? = null,
                      issn: String? = null,
                      publisher: String? = null,
                      affiliation: String? = null,
                      journal: String? = null,
                      volumeNumber: Int? = null,
                      issueNumber: Int? = null,
                      page: Int? = null,
                      references: String? = null,
                      yearFrom: Int? = null,
                      yearTo: Int? = null,
                      articleBodyAvailable: Int? = null,
                      sortOrder: Int? = null): Single<List<ArticleEntity>>

    fun fetchArticle(articleId: Long): Single<ArticleEntity>

    fun fetchAuthors(keyword: String,
                     count: Int? = null,
                     lang: String? = null,
                     startIndex: Int? = null,
                     sortOrder: Int? = null): Single<List<AuthorColumn>>

    fun fetchAuthor(authorId: Long): Single<List<Author>>
}