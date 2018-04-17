package io.gitub.kotako.astraia.data.Entity

import io.realm.RealmObject

open class Author: RealmObject() {
    open var id: Long? = null
    open var link: String? = null
    open var linkJson: String? = null
    open var name: String? = null
    open var nameInEnglish: String? = null
    open var description: String? = null
    open var intersts: List<Topic>? = null
    open var externalLinks: List<Link>? = null
    open var organizations: List<Organization>? = null
}