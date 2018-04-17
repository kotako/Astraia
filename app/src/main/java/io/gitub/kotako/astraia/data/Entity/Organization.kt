package io.gitub.kotako.astraia.data.Entity

import io.realm.RealmObject

open class Organization: RealmObject() {
    open var link: String? = null
    open var name: String? = null
    open var nameInEnglish: String? = null
}