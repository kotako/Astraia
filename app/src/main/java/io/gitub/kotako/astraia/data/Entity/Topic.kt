package io.gitub.kotako.astraia.data.Entity

import java.io.Serializable

interface Topic: Serializable {
    val link: String?
    val title: String?
    val titleInEnglish: String?
}