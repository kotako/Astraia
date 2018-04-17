package io.gitub.kotako.astraia.data.realmObject

import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Entity.Link
import io.gitub.kotako.astraia.data.Entity.Organization
import io.gitub.kotako.astraia.data.Entity.Topic
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmAuthor: Author, RealmObject() {
    @PrimaryKey override var id: Long? = null
    override var link: String? = null
    override var linkJson: String? = null
    override var name: String? = null
    override var nameInEnglish: String? = null
    override var description: String? = null
    override var intersts: List<Topic>? = null
    override var externalLinks: List<Link>? = null
    override var organizations: List<Organization>? = null
}