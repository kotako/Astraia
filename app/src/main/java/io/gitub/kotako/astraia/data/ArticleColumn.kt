package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName
import java.util.*


data class ArticleColumn(
        @SerializedName("@id")
        val link: String = "",

        val title: String = "",

        val description: String = "",

        @SerializedName("rdfs:seeAlso")
        private val linkJsonObject: CiniiData = CiniiData(),

        @SerializedName("dc:creator")
        private val authorsData: List<CiniiData> = emptyList(),

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
        val publishedAt: Date? = null
) {
    val linkJson: String = linkJsonObject.link
    val authors: List<String> = authorsData.map { item -> item.value }
}