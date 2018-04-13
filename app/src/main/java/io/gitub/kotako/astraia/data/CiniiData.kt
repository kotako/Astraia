package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

data class CiniiData(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("dc:title")
        val title: List<CiniiData> = emptyList(),

        @SerializedName("@value")
        val value: String = ""
)