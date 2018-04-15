package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Entity.Organization
import io.gitub.kotako.astraia.data.Entity.Topic

data class AuthorColumnResponseEntity(
        override val id: Long? = null,

        @SerializedName("@id")
        override val link: String = "",

        @SerializedName("title")
        override val name: String = "",

        override val nameInEnglish: String? = null,

        @SerializedName("description")
        override val description: String = "",

        override val externalLinks: List<LinkImpl>? = null,

        override val intersts: List<Topic>? = null,

        override val organizations: List<Organization>? = null
) : Author {
    override val linkJson: String = "$link.json"
}