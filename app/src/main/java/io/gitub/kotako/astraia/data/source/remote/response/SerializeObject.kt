package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.*

// Gsonでデシリアライズする時につかう
// TODO きもちわるいからどうにかしたい😞

data class NameSerialize(
        @SerializedName("@value")
        val name: String? = null,

        @SerializedName("@language")
        val lang: String? = null
)

data class PublisherSerialize(
        @SerializedName("@value")
        override val name: String? = null,

        @SerializedName("@language")
        override val lang: String? = null
): Publisher

data class PublicationSerialize(
        @SerializedName("@value")
        override val name: String? = null,

        @SerializedName("@language")
        override val lang: String? = null
): Publication

data class SourceSerialize(
        @SerializedName("@value")
        override val name: String? = null,

        @SerializedName("@language")
        override val lang: String? = null
): Source

data class LinkSerialize(
        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("dc:title")
        override val title: String? = null
): Link

data class JournalSerialize(
        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("dc:title")
        override val title: String? = null
): Journal

data class AuthorLinkSerialize(
        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("foaf:name")
        private val authorData: List<AuthorOverViewSerialize?>? = null,

        override val description: String? = null,

        override val intersts: List<Topic?>? = null,

        private val externalLinksSerialize: List<LinkSerialize?>? = null,

        private val organizationsSerialize: List<OrganizationSerialize?>? = null,

        override val id: Long? = null
): Author {
    override val externalLinks: List<Link?>? = externalLinksSerialize
    override val linkJson: String? = link?.let { value: String -> "${value.substring(value.length - 3, value.length - 1)}.json" }
    override val organizations: List<Organization?>? = organizationsSerialize
    override val name: String? = authorData?.first { author -> author?.lang.isNullOrBlank() }?.name
    override val nameInEnglish: String? = authorData?.first { author -> author?.lang == "en" }?.name
}

data class AuthorOverViewSerialize(
        @SerializedName("@value")
        val name: String? = null,

        @SerializedName("@language")
        val lang: String? = null,

        @SerializedName("con:organization")
        val organizations: List<OrganizationSerialize?>? = null
)

data class OrganizationSerialize(
        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("foaf:name")
        private val names: List<NameSerialize?>? = null
) : Organization {
    override val name: String? = names?.first { t -> t?.lang.isNullOrEmpty() }?.name
    override val nameInEnglish: String? = names?.first { t -> t?.lang == "en" }?.name
}

data class TopicSerialize(
        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("dc:title")
        private val titles: List<NameSerialize?>? = null
) : Topic {
    override val title: String? = titles?.first { t -> t?.lang.isNullOrBlank() }?.name
    override val titleInEnglish: String? = titles?.first { t -> t?.lang == "en" }?.name
}