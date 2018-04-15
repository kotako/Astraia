package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.*
import io.gitub.kotako.astraia.data.Entity.Author
import java.util.*

data class ArticleResponseEntity(
        @SerializedName("cinii:naid")
        override val id: Long? = null,

        @SerializedName("@id")
        override val link: String = "",

        override val linkJson: String? = null,

        @SerializedName("dc:title")
        private val titles: List<Name>? = null,

        override val description: String? = null,

        @SerializedName("dc:language")
        override val lang: String? = null,

        @SerializedName("foaf:maker")
        override val authors: List<AuthorLink> = emptyList(),

        @SerializedName("dc:publisher")
        override val publishers: List<Name> = emptyList(),

        @SerializedName("prism:publicationName")
        override val publications: List<Name> = emptyList(),

        @SerializedName("dc:source")
        override val sources: List<Name> = emptyList(),

        @SerializedName("prism:issn")
        override val issn: String = "",

        @SerializedName("cinii:ncid")
        override val nacsisId: String = "",

        @SerializedName("cinii:ndljpi")
        override val dnlId: String = "",

        @SerializedName("cinii:references")
        override val citedCount: Int = 0,

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

        @SerializedName("dcterms:isPartOf")
        override val jounal: LinkImpl? = null,

        @SerializedName("rdfs:seeAlso")
        override val relatedLinks: List<LinkImpl> = emptyList(),

        @SerializedName("foaf:topic")
        override val topics: List<Topic> = emptyList(),

        @SerializedName("foaf:depiction")
        override val image: LinkImpl? = null
) : Article {
    override val title: String? = titles?.first { name -> name.lang.isNullOrBlank() }?.name

    override val titleInEnglish: String? = titles?.first { name -> name.lang == "en" }?.name
}

data class Name(
        @SerializedName("@value")
        override val name: String = "",

        @SerializedName("@language")
        override val lang: String? = null
) : Publisher, Publication, Source

data class AuthorLink(
        override val id: Long? = null,

        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("foaf:name")
        private val authorData: List<AuthorOverView>? = null,

        override val description: String? = null,

        override val intersts: List<Topic>? = null,

        override val externalLinks: List<LinkImpl>? = null,

        override val organizations: List<OrganizationImpl>? = null
) : Author {
    //      末尾の#meを削除して.jsonをつける
    override val linkJson: String? = "${link?.substring(link.length - 3, link.length - 1)}.json"

    override val name: String? = authorData?.first { author -> author.lang.isNullOrBlank() }?.name

    override val nameInEnglish: String? = authorData?.first { author -> author.lang == "en" }?.name
}

data class AuthorOverView(
        @SerializedName("@value")
        val name: String = "",

        @SerializedName("@language")
        val lang: String = "",

        @SerializedName("con:organization")
        val organizations: List<Organization> = emptyList()
)

data class OrganizationImpl(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("foaf:name")
        private val names: List<Name>? = null
) : Organization {
    override val name: String? = names?.first { t -> t.lang.isNullOrEmpty() }?.name

    override val nameInEnglish: String? = names?.first { t -> t.lang == "en" }?.name
}
