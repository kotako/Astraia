package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Article
import io.gitub.kotako.astraia.data.ArticleColumn
import io.gitub.kotako.astraia.data.Author
import io.gitub.kotako.astraia.data.AuthorColumn

interface Response {
    val link: String
    val body: List<Any>
}

data class ArticlesResponse(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("@graph")
        override val body: List<ArticlesResponseBody> = emptyList()
) : Response

data class ArticlesResponseBody(
        @SerializedName("items")
        val articles: List<ArticleColumn> = emptyList()
)

data class ArticleResponse(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("@graph")
        override val body: List<Article> = emptyList()
) : Response

data class AuthorsResponse(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("@graph")
        override val body: List<AuthorResponseBody> = emptyList()
) : Response

data class AuthorResponseBody(
        @SerializedName("items")
        val authors: List<AuthorColumn> = emptyList()
)

data class AuthorResponse(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("@graph")
        override val body: List<Author> = emptyList()
) : Response

