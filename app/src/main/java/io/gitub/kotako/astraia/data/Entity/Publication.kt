package io.gitub.kotako.astraia.data.Entity

import java.io.Serializable

interface Publication: Serializable {
    val name: String?
    val lang: String?
}