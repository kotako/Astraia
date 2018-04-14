package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

data class Link(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("dc:title")
        val title: String = ""
)