package io.gitub.kotako.astraia.data.Entity

import io.realm.RealmObject

open class Topic: RealmObject() {
    open var link: String? = null
    open var title: String? = null
    open var titleInEnglish: String? = null
}