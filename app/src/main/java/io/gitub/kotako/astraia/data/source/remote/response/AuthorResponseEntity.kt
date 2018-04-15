package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Entity.Link
import io.gitub.kotako.astraia.data.Entity.Organization
import io.gitub.kotako.astraia.data.Entity.Topic

data class AuthorResponseEntity(
        override val id: Long? = null,

        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("foaf:name")
        private val names: List<Name>? = null,

        override val description: String? = null,

        @SerializedName("foaf:interest")
        override val intersts: List<TopicImpl> = emptyList(),

        @SerializedName("rdfs:seeAlso")
        override val externalLinks: List<LinkImpl> = emptyList(),

        override val organizations: List<Organization>? = emptyList()
) : Author {
    override val linkJson: String? = link?.substring(link.length - 3, link.length - 1)

    override val name: String? = names?.first { t -> t.lang.isNullOrBlank() }?.name

    override val nameInEnglish: String? = names?.first { t -> t.lang == "en" }?.name
}

data class TopicImpl(
        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("dc:title")
        private val titles: List<Name>? = null
) : Topic {
    override val title: String? = titles?.first { t -> t.lang.isNullOrBlank() }?.name

    override val titleInEnglish: String? = titles?.first { t -> t.lang == "en" }?.name
}