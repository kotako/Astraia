package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName

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
        val articles: List<ArticleColumnResponseEntity> = emptyList()
)

data class ArticleResponse(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("@graph")
        override val body: List<ArticleResponseEntity> = emptyList()
) : Response

data class AuthorsResponse(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("@graph")
        override val body: List<AuthorResponseBody> = emptyList()
) : Response

data class AuthorResponseBody(
        @SerializedName("items")
        val authors: List<AuthorColumnResponseEntity> = emptyList()
)

data class AuthorResponse(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("@graph")
        override val body: List<AuthorResponseEntity> = emptyList()
) : Response

