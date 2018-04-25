package io.gitub.kotako.astraia.data.Entity

import java.io.Serializable

interface Publisher: Serializable {
    val name: String?
    val lang: String?
}