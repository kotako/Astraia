package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Article
import io.gitub.kotako.astraia.data.ArticleColumn
import io.gitub.kotako.astraia.data.Author

data class ArticlesResponse (
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("@graph")
        val value: List<ArticleColumn>
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
        val value: List<Author>
)

