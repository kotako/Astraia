package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

data class Author(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("foaf:name")
        private val nameData: List<CiniiData> = emptyList(),

        @SerializedName("rdfs:seeAlso")
        val relatedLinks: List<CiniiData> = emptyList(),

        @SerializedName("foaf:interest")
        val interest: List<CiniiData> = emptyList()

) {
    val names: List<String> = nameData.map { item -> item.value }
}