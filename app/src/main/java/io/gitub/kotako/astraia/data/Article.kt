package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

interface ArticleEntity {
    val link: String
    val linkJson: Link
    val titles: List<Name>
    val publishers: List<Name>
    val publications: List<Name>
    val issn: String
    val description: String
    val journalNumber: Int
    val issueNumber: Int
    val startingPage: Int
    val endingPage: Int
    val publishedAt: String
    val id: Long
    val nacsisId: String
    val dnlId: String
    val lang: String
    val citedCount: Int
    val sources: List<Name>
    val jounal: Link
    val relatedLinks: List<Link>
    val topics: List<Names>
    val image: Link
    val authors: List<AuthorLink>
}

data class Article(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("dc:title")
        override val titles: List<Name> = emptyList(),

        @SerializedName("dc:publisher")
        override val publishers: List<Name> = emptyList(),

        @SerializedName("prism:publicationName")
        override val publications: List<Name> = emptyList(),

        @SerializedName("prism:issn")
        override val issn: String = "",

        @SerializedName("prism:volume")
        override val journalNumber: Int = 0,

        @SerializedName("prism:number")
        override val issueNumber: Int = 0,

        @SerializedName("prism:startingPage")
        override val startingPage: Int = 0,

        @SerializedName("prism:endingPage")
        override val endingPage: Int = 0,

        @SerializedName("prism:publicationDate")
        override val publishedAt: String = "",

        @SerializedName("cinii:naid")
        override val id: Long = 0L,

        @SerializedName("cinii:ncid")
        override val nacsisId: String = "",

        @SerializedName("cinii:ndljpi")
        override val dnlId: String = "",

        @SerializedName("dc:language")
        override val lang: String = "",

        @SerializedName("cinii:references")
        override val citedCount: Int = 0,

        @SerializedName("dc:source")
        override val sources: List<Name> = emptyList(),

        @SerializedName("dcterms:isPartOf")
        override val jounal: Link = Link(),

        @SerializedName("rdfs:seeAlso")
        override val relatedLinks: List<Link> = emptyList(),

        @SerializedName("foaf:topic")
        override val topics: List<Names> = emptyList(),

        @SerializedName("foaf:depiction")
        override val image: Link = Link(),

        @SerializedName("foaf:maker")
        override val authors: List<AuthorLink> = emptyList(),

        override val description: String = "",

        override val linkJson: Link = Link()
): ArticleEntity

data class Name(
        @SerializedName("@value")
        val name: String = "",

        @SerializedName("@language")
        val lang: String = ""
)

data class Names(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("dc:title")
        val names: List<Name> = emptyList()
)

data class AuthorLink(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("foaf:name")
        val authorData: List<AuthorOverView> = emptyList()
)

data class AuthorOverView(
        @SerializedName("@value")
        val name: String = "",

        @SerializedName("@language")
        val lang: String = "",

        @SerializedName("con:organization")
        val organizations: List<Organization> = emptyList()
)

data class Organization(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("foaf:name")
        val names: List<Name> = emptyList()
)
