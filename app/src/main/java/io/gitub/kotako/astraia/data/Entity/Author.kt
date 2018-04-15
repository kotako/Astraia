package io.gitub.kotako.astraia.data.Entity

interface Author {
    val id: Long?
    val link: String?
    val linkJson: String?
    val name: String?
    val nameInEnglish: String?
    val description: String?
    val intersts: List<Topic>?
    val externalLinks: List<Link>?
    val organizations: List<Organization>?
}