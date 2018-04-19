package io.gitub.kotako.astraia.data.source

data class Query(
        var keyword: String,
        var count: Int = 20,
        var startIndex: Int? = null,
        var lang: String? = null,
        var title: String? = null,
        var author: String? = null,
        var authorId: Long? = null,
        var publisher: String? = null,
        var affilication: String? = null,
        var journal: String? = null,
        var yearFrom: Int? = null,
        var yearTo: Int? = null,
        var sort: Int = 1,
        var issn: String? = null,
        var page: Int? = null,
        var articleBodyAvailable: Int = 0
)
