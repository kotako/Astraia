package io.gitub.kotako.astraia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Entity.Link
import io.gitub.kotako.astraia.data.Entity.Organization
import io.gitub.kotako.astraia.data.Entity.Topic
import java.io.Serializable

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
) : Author, Serializable {
    override val id: Long?
        get() = link?.takeLast("https://ci.nii.ac.jp/naid/".length)?.toLong()
    override val linkJson: String?
        get() = "$link.json"
}