package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Entity.Link
import io.gitub.kotako.astraia.data.Entity.Organization
import io.gitub.kotako.astraia.data.Entity.Topic

data class AuthorColumnResponseEntity(
        @SerializedName("@id")
        override val link: String? = null,

        @SerializedName("title")
        override val name: String? = null,

        @SerializedName("description")
        override val description: String? = null,

        override val externalLinks: List<Link?>? = null,

        override val intersts: List<Topic?>? = null,

        override val nameInEnglish: String? = null,

        override val organizations: List<Organization?>? = null
) : Author {
    override val id: Long? = link?.dropLast(12)?.toLong()
    override val linkJson: String? = "$link.json"
}