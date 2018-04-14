package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

data class Author(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("foaf:name")
        val names: List<AuthorName> = emptyList(),

        @SerializedName("foaf:interest")
        val intersts: List<Interest> = emptyList(),

        @SerializedName("rdfs:seeAlso")
        val externalLinks: List<Link> = emptyList()
)

data class Interest(
        @SerializedName("id")
        val link: String = "",

        @SerializedName("dc:title")
        val titles: List<IntrestTitle> = emptyList()
)

data class IntrestTitle(
        @SerializedName("@value")
        val title: String = "",

        @SerializedName("@language")
        val lang: String = ""
)