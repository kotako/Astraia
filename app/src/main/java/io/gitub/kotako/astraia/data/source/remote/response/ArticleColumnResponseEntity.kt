package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.*

data class ArticleColumnResponseEntity(
        override val id: Long? = null,

        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("rdfs:seeAlso")
        private val linkJsonData: LinkImpl? = null,

        override val title: String = "",

        override val titleInEnglish: String? = null,

        override val description: String = "",

        @SerializedName("@language")
        override val lang: String = "",

        @SerializedName("dc:creator")
        private val authorsName: List<Name>? = null,

        @SerializedName("dc:publisher")
        private val publisher: String = "",

        @SerializedName("prism:publicationName")
        private val publication: String = "",

        override val sources: List<Name>? = null,

        @SerializedName("prism:issn")
        override val issn: String = "",

        override val nacsisId: String? = null,

        override val dnlId: String? = null,

        override val citedCount: Int? = null,

        @SerializedName("prism:volume")
        override val journalNumber: Int = 0,

        @SerializedName("prism:number")
        override val issueNumber: Int = 0,

        @SerializedName("prism:startingPage")
        override val startingPage: Int = 0,

        @SerializedName("prism:endingPage")
        override val endingPage: Int = 0,

        @SerializedName("prism:publicationDate")
        override val publishedAt: String? = null,

        override val jounal: Journal? = null,

        override val relatedLinks: List<LinkImpl>? = null,

        override val topics: List<Topic>? = null,

        override val image: LinkImpl? = null
) : Article {
    override val linkJson: String? = linkJsonData?.link

    override val authors: List<AuthorLink>? = authorsName?.map { author -> AuthorLink(authorData = listOf(AuthorOverView(name = author.name))) }

    override val publishers: List<Name> = listOf(Name(name = publisher))

    override val publications: List<Name> = listOf(Name(name = publication))
}

data class LinkImpl(
        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("dc:title")
        override val title: String? = null
): Link, Journal