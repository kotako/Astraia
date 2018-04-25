package io.gitub.kotako.astraia.data.Entity

import java.io.Serializable

interface Link: Serializable {
    val link: String?
    val title: String?
}