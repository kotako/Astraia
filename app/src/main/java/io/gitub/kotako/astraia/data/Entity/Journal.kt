package io.gitub.kotako.astraia.data.Entity

import io.realm.RealmObject

open class Journal: RealmObject() {
    open var link: String? = null
    open var title: String? = null
}