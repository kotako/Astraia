package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class Article(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("dc:title")
        private val titleData: CiniiData = CiniiData(),

        @SerializedName("dc:publisher")
        private val publisherData: List<CiniiData> = emptyList(),

        @SerializedName("prism:publicationName")
        private val publicationData: List<CiniiData> = emptyList(),

        @SerializedName("prism:issn")
        val issn: String = "",

        @SerializedName("prism:volume")
        val journalNumber: Int = 0,

        @SerializedName("prism:number")
        val issueNumber: Int = 0,

        @SerializedName("prism:startingPage")
        val startingPage: Int = 0,

        @SerializedName("prism:endingPage")
        val endingPage: Int = 0,

        @SerializedName("prism:publicationDate")
        val publishedAt: Date? = null,

        @SerializedName("dc:description")
        private val descriptionData: CiniiData = CiniiData(),

        @SerializedName("cinii:naid")
        val id: Long = 0,

        @SerializedName("cinii:ncid")
        val nacsisId: String = "",

        @SerializedName("cinii:ndljpi")
        val dnlId: String = "",

        @SerializedName("dc:language")
        val lang: String = "",

        @SerializedName("cinii:references")
        val citedCount: Int = 0,

        @SerializedName("dc:source")
        private val sourceData: List<CiniiData> = emptyList(),

        @SerializedName("dcterms:isPartOf")
        val jounal: CiniiData = CiniiData(),

        @SerializedName("rdfs:seeAlso")
        val relatedLinks: List<CiniiData> = emptyList(),

        @SerializedName("foaf:topic")
        val topics: List<CiniiData> = emptyList(),

        @SerializedName("foaf:depiction")
        private val imageData: CiniiData = CiniiData(),

        @SerializedName("foaf:maker")
        val authors: List<Author> = emptyList()
) {
    val title: String = titleData.value
    val description: String = descriptionData.value
    val publisher: List<String> = publisherData.map { item -> item.value }
    val publication: List<String> = publicationData.map { item -> item.value }
    val source: List<String> = sourceData.map { item -> item.value }
    val imageUrl: String = imageData.link
}