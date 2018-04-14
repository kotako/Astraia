package io.gitub.kotako.astraia.data

import com.google.gson.annotations.SerializedName

data class ArticleColumn(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("@language")
        override val lang: String = "",

        private val title: String = "",

        override val description: String = "",

        @SerializedName("rdfs:seeAlso")
        override val linkJson: Link = Link(),

        @SerializedName("dc:creator")
        private val authorsName: List<Name> = emptyList(),

        @SerializedName("dc:publisher")
        private val publisher: String = "",

        @SerializedName("prism:publicationName")
        private val publication: String = "",

        @SerializedName("prism:volume")
        override val journalNumber: Int = 0,

        @SerializedName("prism:number")
        override val issueNumber: Int = 0,

        @SerializedName("prism:issn")
        override val issn: String = "",

        @SerializedName("prism:startingPage")
        override val startingPage: Int = 0,

        @SerializedName("prism:endingPage")
        override val endingPage: Int = 0,

        @SerializedName("prism:publicationDate")
        override val publishedAt: String = "",

        override val dnlId: String = "",
        override val image: Link = Link(),
        override val jounal: Link = Link(),
        override val nacsisId: String = "",
        override val relatedLinks: List<Link> = emptyList(),
        override val sources: List<Name> = emptyList(),
        override val topics: List<Names> = emptyList(),
        override val citedCount: Int = 0,
        override val id: Long = 0L,
        override val titles: List<Name> = listOf(Name(name = title)),
        override val publishers: List<Name> = listOf(Name(name = publisher)),
        override val publications: List<Name> = listOf(Name(name = publication)),
        override val authors: List<AuthorLink> = authorsName.map { author -> AuthorLink(authorData = listOf(AuthorOverView(name = author.name, lang = author.lang))) }
) : ArticleEntity
