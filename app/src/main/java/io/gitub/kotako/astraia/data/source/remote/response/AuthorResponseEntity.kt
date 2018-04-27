package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Entity.Link
import io.gitub.kotako.astraia.data.Entity.Organization
import io.gitub.kotako.astraia.data.Entity.Topic
import java.io.Serializable

data class AuthorResponseEntity(
        override val id: Long? = null,

        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("foaf:name")
        private val namesSerialize: List<NameSerialize?>? = null,

        @SerializedName("foaf:interest")
        private val interstsSerialize: List<TopicSerialize?>? = null,

        @SerializedName("rdfs:seeAlso")
        private val externalLinksSerialize: List<LinkSerialize?>? = null,

        override val description: String? = null,

        override val organizations: List<Organization>? = null
) : Author, Serializable {
    override val linkJson: String? = link?.let { value: String -> value.substring(value.length - 3, value.length - 1) }
    override val name: String? = namesSerialize?.first { t -> t?.lang.isNullOrBlank() }?.name
    override val nameInEnglish: String? = namesSerialize?.first { t -> t?.lang == "en" }?.name
    override val intersts: List<Topic?>? = interstsSerialize
    override val externalLinks: List<Link?>? = externalLinksSerialize
}
