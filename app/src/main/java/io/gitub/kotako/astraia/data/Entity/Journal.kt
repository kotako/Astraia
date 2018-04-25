package io.gitub.kotako.astraia.data.Entity

import java.io.Serializable

interface Journal: Serializable {
    val link: String?
    val title: String?
}