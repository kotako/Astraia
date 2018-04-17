package io.gitub.kotako.astraia.data.Entity

import io.realm.RealmObject

open class Publisher: RealmObject() {
    open var name: String? = null
    open var lang: String? = null
}