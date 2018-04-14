package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

data class AuthorColumn(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("title")
        val name: String = "",

        @SerializedName("description")
        val description: String = "",

        @SerializedName("dc:subject")
        val subject: String = "",

        @SerializedName("rdfs:seeAlso")
        val linkJson: Link = Link(),

        @SerializedName("dc:date")
        val updateAt: String = ""
)