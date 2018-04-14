package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

data class Author(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("foaf:name")
        val names: List<Name> = emptyList(),

        @SerializedName("foaf:interest")
        val intersts: List<Names> = emptyList(),

        @SerializedName("rdfs:seeAlso")
        val externalLinks: List<Link> = emptyList()
)
