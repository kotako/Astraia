package io.gitub.kotako.astraia.data.Entity

import io.realm.RealmObject

open class Source: RealmObject() {
    open var name: String? = null
    open var lang: String? = null
}