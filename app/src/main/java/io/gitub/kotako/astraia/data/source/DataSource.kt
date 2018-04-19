package io.gitub.kotako.astraia.data.source

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.realm.RealmArticle
import io.reactivex.Completable
import io.reactivex.Single

interface DataSource {
    fun fetchArticles(q: Query): Single<List<Article>>

    fun fetchArticle(articleId: Long): Single<Article>

    fun fetchAuthors(q: Query): Single<List<Author>>

    fun fetchAuthor(authorId: Long): Single<List<Author>>

    fun addReadLatorArticle(article: Article): Completable

    fun addFavoriteArticle(article: Article): Completable

    fun readLatorArticles(): Single<List<RealmArticle>>

    fun readLatorArticlesLiveData(): Single<LiveRealmData<RealmArticle>>

    fun favoriteArticles(): Single<List<RealmArticle>>

    fun favoriteArticlesLiveData(): Single<LiveRealmData<RealmArticle>>
}