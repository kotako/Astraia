package io.gitub.kotako.astraia.data.source.local

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Query
import io.gitub.kotako.astraia.data.realmObject.FavoriteArticle
import io.gitub.kotako.astraia.data.realmObject.LiveRealmData
import io.gitub.kotako.astraia.data.realmObject.ReadLatorArticle
import io.gitub.kotako.astraia.data.source.DataSource
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm
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
        realmClient.executeTransactionAsync(
                { transaction: Realm -> transaction.copyToRealmOrUpdate(article as FavoriteArticle) },
                { error: Throwable -> result = Completable.error(error) }
        )
        return result
    }

    override fun addReadLatorArticle(article: Article): Completable {
        var result: Completable = Completable.complete()
        realmClient.executeTransactionAsync(
                { transition: Realm -> transition.copyToRealmOrUpdate(article as ReadLatorArticle) },
                { error: Throwable? -> result = Completable.error(error) }
        )
        return result
    }

    override fun readLatorArticles(): Single<List<Article>> {
        var result: List<Article> = emptyList()
        realmClient.executeTransaction { transaction ->
            result = transaction.copyFromRealm(transaction.where(ReadLatorArticle::class.java).findAllAsync())
        }
        return Single.just(result)
    }

    override fun favoriteArticles(): Single<List<Article>> {
        var result: List<Article> = emptyList()
        realmClient.executeTransaction { transaction ->
            result = transaction.copyFromRealm(transaction.where(FavoriteArticle::class.java).findAllAsync())
        }
        return Single.just(result)
    }

    override fun readLatorArticlesLiveData(): Single<LiveRealmData<ReadLatorArticle>> =
            Single.just(LiveRealmData(realmClient.where(ReadLatorArticle::class.java).findAllAsync()))

    override fun favoriteArticlesLiveData(): Single<LiveRealmData<FavoriteArticle>> =
            Single.just(LiveRealmData(realmClient.where(FavoriteArticle::class.java).findAllAsync()))
}