package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

data class Article(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("dc:title")
        val titles: List<ArticleTitle> = emptyList(),

        @SerializedName("dc:publisher")
        val publishers: List<Publisher> = emptyList(),

        @SerializedName("prism:publicationName")
        val publications: List<Publication> = emptyList(),

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
        val publishedAt: String = "",

        @SerializedName("cinii:naid")
        val id: Long = 0L,

        @SerializedName("cinii:ncid")
        val nacsisId: String = "",

        @SerializedName("cinii:ndljpi")
        val dnlId: String = "",

        @SerializedName("dc:language")
        val lang: String = "",

        @SerializedName("cinii:references")
        val citedCount: Int = 0,

        @SerializedName("dc:source")
        val sources: List<Source> = emptyList(),

        @SerializedName("dcterms:isPartOf")
        val jounal: Journal = Journal(),

        @SerializedName("rdfs:seeAlso")
        val relatedLinks: List<Link> = emptyList(),

        @SerializedName("foaf:topic")
        val topics: List<Topic> = emptyList(),

        @SerializedName("foaf:depiction")
        val image: Link = Link(),

        @SerializedName("foaf:maker")
        val authors: List<AuthorLink> = emptyList()
)

data class ArticleTitle(
        @SerializedName("@value")
        val name: String = "",

        @SerializedName("@language")
        val lang: String = ""
)

data class Publication(
        @SerializedName("@value")
        val name: String = "",

        @SerializedName("@language")
        val lang: String = ""
)

data class Publisher (
        @SerializedName("@value")
        val name: String = "",

        @SerializedName("@language")
        val lang: String = ""
)

data class Source(
        @SerializedName("@value")
        val name: String = ""
)

data class Journal(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("dc:title")
        val title: String = ""
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
        val names: List<OrganizationName> = emptyList()
)

data class OrganizationName(
        @SerializedName("@value")
        val name: String = "",

        @SerializedName("@language")
        val lang: String = ""
)


data class Topic(
        @SerializedName("@id")
        val link: String = "",

        @SerializedName("dc:title")
        val titles: List<TopicTitle> = emptyList()
)

data class TopicTitle(
        @SerializedName("@value")
        val title: String = "",

        @SerializedName("@language")
        val lang: String = ""
)