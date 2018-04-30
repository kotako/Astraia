package io.gitub.kotako.astraia.data.Entity

import java.io.Serializable

interface Article: Serializable {
    val id: Long?
    val link: String?
    val linkJson: String?
    val title: String?
    val titleInEnglish: String?
    val description: String?
    val lang: String?
    val authors: List<Author?>?
    val publishers: List<Publisher?>?
    val publications: List<Publication?>?
    val sources: List<Source?>?
    val issn: String?
    val nacsisId: String?
    val dnlId: String?
    val citedCount: Int?
    val journalNumber: Int?
    val issueNumber: Int?
    val startingPage: Int?
    val endingPage: Int?
    val publishedAt: String?
    val jounal: Journal?
    val relatedLinks: List<Link?>?
    val topics: List<Topic?>?
    val image: Link?
    val publishersString: String?
    val authorsString: String?
}