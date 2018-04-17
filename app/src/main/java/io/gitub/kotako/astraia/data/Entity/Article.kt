package io.gitub.kotako.astraia.data.Entity

import io.realm.RealmObject

open class Article : RealmObject() {
    open var id: Long? = null
    open var link: String? = null
    open var linkJson: String? = null
    open var title: String? = null
    open var titleInEnglish: String? = null
    open var description: String? = null
    open var lang: String? = null
    open var authors: List<Author>? = null
    open var publishers: List<Publisher>? = null
    open var publications: List<Publication>? = null
    open var sources: List<Source>? = null
    open var issn: String? = null
    open var nacsisId: String? = null
    open var dnlId: String? = null
    open var citedCount: Int? = null
    open var journalNumber: Int? = null
    open var issueNumber: Int? = null
    open var startingPage: Int? = null
    open var endingPage: Int? = null
    open var publishedAt: String? = null
    open var jounal: Journal? = null
    open var relatedLinks: List<Link>? = null
    open var topics: List<Topic>? = null
    open var image: Link? = null
}