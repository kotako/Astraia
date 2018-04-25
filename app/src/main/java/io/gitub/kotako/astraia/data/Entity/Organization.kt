package io.gitub.kotako.astraia.data.Entity

import java.io.Serializable

interface Organization: Serializable {
    val link: String?
    val name: String?
    val nameInEnglish: String?
}