package io.gitub.kotako.astraia.data.source

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.Query
import io.gitub.kotako.astraia.data.realmObject.FavoriteArticle
import io.gitub.kotako.astraia.data.realmObject.LiveRealmData
import io.gitub.kotako.astraia.data.realmObject.ReadLatorArticle
import io.reactivex.Completable
import io.reactivex.Single

interface DataSource {
    fun fetchArticles(q: Query): Single<List<Article>>

    fun fetchArticle(articleId: Long): Single<Article>

    fun fetchAuthors(q: Query): Single<List<Author>>

    fun fetchAuthor(authorId: Long): Single<List<Author>>

    fun addReadLatorArticle(article: Article): Completable

    fun addFavoriteArticle(article: Article): Completable

    fun readLatorArticles(): Single<List<Article>>

    fun readLatorArticlesLiveData(): Single<LiveRealmData<ReadLatorArticle>>

    fun favoriteArticles(): Single<List<Article>>

    fun favoriteArticlesLiveData(): Single<LiveRealmData<FavoriteArticle>>
}