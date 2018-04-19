package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.*

data class ArticleResponseEntity(
        @SerializedName("cinii:naid")
        override val id: Long? = null,

        @SerializedName("@id")
        override val link: String? = null,

        override val linkJson: String? = null,

        @SerializedName("dc:title")
        private val titles: List<NameSerialize?>? = null,

        override val description: String? = null,

        @SerializedName("dc:language")
        override val lang: String? = null,

        @SerializedName("foaf:maker")
        private val authorsSerialize: List<AuthorLinkSerialize?>? = null,

        @SerializedName("dc:publisher")
        private val publishersSerialize: List<PublisherSerialize?>? = null,

        @SerializedName("prism:publicationName")
        private val publicationsSerialize: List<PublicationSerialize?>? = null,

        @SerializedName("dc:source")
        private val sourcesSerialize: List<SourceSerialize?>? = null,

        @SerializedName("prism:issn")
        override val issn: String? = null,

        @SerializedName("cinii:ncid")
        override val nacsisId: String? =  null,

        @SerializedName("cinii:ndljpi")
        override val dnlId: String? = null,

        @SerializedName("cinii:references")
        override val citedCount: Int? = null,

        @SerializedName("prism:volume")
        override val journalNumber: Int? = null,

        @SerializedName("prism:number")
        override val issueNumber: Int? = null,

        @SerializedName("prism:startingPage")
        override val startingPage: Int? = null,

        @SerializedName("prism:endingPage")
        override val endingPage: Int? = null,

        @SerializedName("prism:publicationDate")
        override val publishedAt: String? = null,

        @SerializedName("dcterms:isPartOf")
        private val jounalSerialize: JournalSerialize? = null,

        @SerializedName("rdfs:seeAlso")
        private val relatedLinksSerialize: List<LinkSerialize?>? = null,

        @SerializedName("foaf:topic")
        private val topicsSerialize: List<TopicSerialize?>? = null,

        @SerializedName("foaf:depiction")
        private val imageSerialize: LinkSerialize? = null,

        override val authors: List<Author?>? = null
) : Article {
    override val title: String? = titles?.first { name -> name?.lang.isNullOrBlank() }?.name
    override val titleInEnglish: String? = titles?.first { name -> name?.lang == "en" }?.name
    override val publishers: List<Publisher?>? = publishersSerialize
    override val publications: List<Publication?>? = publicationsSerialize
    override val sources: List<Source?>? = sourcesSerialize
    override val image: Link? = imageSerialize
    override val jounal: Journal? = jounalSerialize
    override val relatedLinks: List<Link?>? = relatedLinksSerialize
    override val topics: List<Topic?>? = topicsSerialize
}
