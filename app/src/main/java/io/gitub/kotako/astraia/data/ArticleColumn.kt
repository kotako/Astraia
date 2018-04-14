package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

data class ArticleColumn(
        @SerializedName("@id")
        val link: String = "",

        val title: String = "",

        val description: String = "",

        @SerializedName("rdfs:seeAlso")
        val linkJson: Link = Link(),

        @SerializedName("dc:creator")
        val authors: List<AuthorName> = emptyList(),

        @SerializedName("dc:publisher")
        val publisher: String = "",

        @SerializedName("prism:publicationName")
        val publication: String = "",

        @SerializedName("prism:volume")
        val journalNumber: Int = 0,

        @SerializedName("prism:number")
        val issueNumber: Int = 0,

        @SerializedName("prism:issn")
        val issn: String = "",

        @SerializedName("prism:startingPage")
        val startingPage: Int = 0,

        @SerializedName("prism:endingPage")
        val endingPage: Int = 0,

        @SerializedName("prism:publicationDate")
        val publishedAt: String = ""
)

data class AuthorName(
        @SerializedName("@value")
        val name: String = "",

        @SerializedName("@language")
        val lang: String = ""
)