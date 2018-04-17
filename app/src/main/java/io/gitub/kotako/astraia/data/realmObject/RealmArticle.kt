package io.gitub.kotako.astraia.data.realmObject

import io.gitub.kotako.astraia.data.Entity.*
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmArticle: Article, RealmObject() {
    @PrimaryKey override var id: Long? = null
    override var link: String? = null
    override var linkJson: String? = null
    override var title: String? = null
    override var titleInEnglish: String? = null
    override var description: String? = null
    override var lang: String? = null
    override var authors: List<Author>? = null
    override var publishers: List<Publisher>? = null
    override var publications: List<Publication>? = null
    override var sources: List<Source>? = null
    override var issn: String? = null
    override var nacsisId: String? = null
    override var dnlId: String? = null
    override var citedCount: Int? = null
    override var journalNumber: Int? = null
    override var issueNumber: Int? = null
    override var startingPage: Int? = null
    override var endingPage: Int? = null
    override var publishedAt: String? = null
    override var jounal: Journal? = null
    override var relatedLinks: List<Link>? = null
    override var topics: List<Topic>? = null
    override var image: Link? = null
}