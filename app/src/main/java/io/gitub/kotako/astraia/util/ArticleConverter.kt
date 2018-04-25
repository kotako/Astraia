package io.gitub.kotako.astraia.util

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.realm.RealmArticle
import io.gitub.kotako.astraia.data.realm.RealmGroup
import io.realm.RealmList

fun Article.toRealmObject(group: RealmGroup? = null): RealmArticle = RealmArticle(
        id = id,
        group = group?.name,
        link = link,
        linkJson = linkJson,
        title = title,
        titleInEnglish = titleInEnglish,
        description = description,
        authors = RealmList<String?>().apply {
            authors?.forEach { author -> add(author?.name) }
        }
)