package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Article
import io.gitub.kotako.astraia.data.ArticleColumn
import io.gitub.kotako.astraia.data.Author
import io.gitub.kotako.astraia.data.AuthorColumn

data class ArticlesResponse (
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("@graph")
        val body: List<ArticlesResponseBody>
)

data class ArticlesResponseBody (
        @SerializedName("items")
        val articles: List<ArticleColumn>
)

data class ArticleResponse (
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("@graph")
        val value: List<Article>
)

data class AuthorsResponse (
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("@graph")
        val body: List<AuthorResponseBody>
)

data class AuthorResponseBody (
        @SerializedName("items")
        val authors: List<AuthorColumn>
)

data class AuthorResponse (
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("@graph")
        val body: List<Author> = emptyList()
)

