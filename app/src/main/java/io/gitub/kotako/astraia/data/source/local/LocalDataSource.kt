package io.gitub.kotako.astraia.data.source.local

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.realm.RealmArticle
import io.gitub.kotako.astraia.data.realm.RealmGroup
import io.gitub.kotako.astraia.data.source.Query
import io.gitub.kotako.astraia.data.source.DataSource
import io.gitub.kotako.astraia.data.source.LiveRealmData
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmList
import javax.inject.Inject

class LocalDataSource @Inject constructor(
        private val realmClient: Realm
) : DataSource {
    override fun fetchArticle(articleId: Long): Single<Article> {
        return Single.never()
    }

    override fun fetchArticles(q: Query): Single<List<Article>> {
        return Single.never()
    }

    override fun fetchAuthor(authorId: Long): Single<List<Author>> {
        return Single.never()
    }

    override fun fetchAuthors(q: Query): Single<List<Author>> {
        return Single.never()
    }

    override fun addFavoriteArticle(article: Article): Completable {
        var result: Completable = Completable.complete()
        val realmArticle = RealmArticle().apply {
            id = article.id
            group = RealmGroup.Favorite.name
            link = article.link
            linkJson = article.linkJson
            title = article.title
            titleInEnglish = article.titleInEnglish
            description = article.description
            authors = article.authors?.map { author -> author?.name } as RealmList<String?>?
        }
        realmClient.executeTransactionAsync(
                { transaction: Realm -> transaction.copyToRealmOrUpdate(realmArticle) },
                { error: Throwable -> result = Completable.error(error) }
        )
        return result
    }

    override fun addReadLatorArticle(article: Article): Completable {
        var result: Completable = Completable.complete()
        val realmArticle = RealmArticle().apply {
            id = article.id
            group = RealmGroup.ReadLater.name
            link = article.link
            linkJson = article.linkJson
            title = article.title
            titleInEnglish = article.titleInEnglish
            description = article.description
            authors = article.authors?.map { author -> author?.name } as RealmList<String?>?
        }
        realmClient.executeTransactionAsync(
                { transition: Realm -> transition.copyToRealmOrUpdate(realmArticle) },
                { error: Throwable? -> result = Completable.error(error) }
        )
        return result
    }

    override fun readLatorArticles(): Single<List<RealmArticle>> =
            Single.just(realmClient.copyFromRealm(realmClient.where(RealmArticle::class.java).equalTo("group", RealmGroup.ReadLater.name).findAllAsync()))

    override fun favoriteArticles(): Single<List<RealmArticle>> =
            Single.just(realmClient.copyFromRealm(realmClient.where(RealmArticle::class.java).equalTo("group", RealmGroup.Favorite.name).findAllAsync()))

    override fun readLatorArticlesLiveData(): Single<LiveRealmData<RealmArticle>> =
            Single.just(LiveRealmData(realmClient.where(RealmArticle::class.java).equalTo("group", RealmGroup.ReadLater.name).findAllAsync()))

    override fun favoriteArticlesLiveData(): Single<LiveRealmData<RealmArticle>> =
            Single.just(LiveRealmData(realmClient.where(RealmArticle::class.java).equalTo("group", RealmGroup.Favorite.name).findAllAsync()))
}