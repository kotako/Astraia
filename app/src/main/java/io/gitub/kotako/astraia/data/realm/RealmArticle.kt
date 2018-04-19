package io.gitub.kotako.astraia.data.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmArticle(
        @PrimaryKey
        var id: Long? = null,
        var link: String? = null,
        var linkJson: String? = null,
        var title: String? = null,
        var titleInEnglish: String? = null,
        var description: String? = null,
        var authors: RealmList<String?>? = null
): RealmObject()