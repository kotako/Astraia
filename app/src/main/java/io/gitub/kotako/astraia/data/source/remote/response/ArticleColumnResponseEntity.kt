package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.*
import java.io.Serializable

data class ArticleColumnResponseEntity(
        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("rdfs:seeAlso")
        private val linkJsonData: LinkSerialize? = null,

        override val title: String? = null,

        override val citedCount: Int? = null,

        override val dnlId: String? = null,

        override val image: Link? = null,

        override val jounal: Journal? = null,

        override val nacsisId: String? = null,

        override val relatedLinks: List<Link?>? = null,

        override val sources: List<Source?>? = null,

        override val titleInEnglish: String? = null,

        override val topics: List<Topic?>? = null,

        override val description: String? = null,

        override val descriptionEnglish: String? = null,

        @SerializedName("@language")
        override val lang: String? = null,

        @SerializedName("dc:creator")
        private val authorsName: List<NameSerialize?>? = null,

        @SerializedName("dc:publisher")
        private val publisher: String? = null,

        @SerializedName("prism:publicationName")
        private val publication: String? = null,

        @SerializedName("prism:issn")
        override val issn: String? = null,

        @SerializedName("prism:volume")
        override val journalNumber: Int? = null,

        @SerializedName("prism:number")
        override val issueNumber: Int? = null,

        @SerializedName("prism:startingPage")
        override val startingPage: String? = null,

        @SerializedName("prism:endingPage")
        override val endingPage: String? = null,

        @SerializedName("prism:publicationDate")
        override val publishedAt: String? = null
) : Article, Serializable {
    override val id: Long?
        get() = link?.drop("https://ci.nii.ac.jp/naid/".length)?.toLong()
    override val linkJson: String?
        get() = linkJsonData?.link
    override val authors: List<Author?>?
        get() = authorsName?.map { author -> AuthorLinkSerialize(authorData = listOf(AuthorOverViewSerialize(name = author?.name))) as Author }
    override val publishers: List<Publisher?>?
        get() = listOf(PublisherSerialize(name = publisher) as Publisher)
    override val publications: List<Publication?>?
        get() = listOf(PublicationSerialize(name = publication) as Publication)
    override val authorsString: String?
        get() = authorsName?.joinToString(separator = ", ", transform = { it?.name ?: "" })
    override val publishersString: String?
        get() = publishers?.joinToString(separator = ", ", transform = { it?.name ?: "" })
}
