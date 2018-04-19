package io.gitub.kotako.astraia.data.source

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.realm.RealmArticle
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

open class ArticleRepository @Inject constructor(
        private val remoteDataSource: DataSource,
        private val localDataSource: DataSource
) : DataSource {

    override fun fetchArticle(articleId: Long): Single<Article> {
        return remoteDataSource.fetchArticle(articleId = articleId)
    }

    override fun fetchArticles(q: Query): Single<List<Article>> {
        return remoteDataSource.fetchArticles(q)
    }

    override fun fetchAuthor(authorId: Long): Single<List<Author>> {
        return remoteDataSource.fetchAuthor(authorId = authorId)
    }

    override fun fetchAuthors(q: Query): Single<List<Author>> {
        return remoteDataSource.fetchAuthors(q)
    }

    override fun addFavoriteArticle(article: Article): Completable {
        return remoteDataSource.addFavoriteArticle(article)
    }

    override fun addReadLatorArticle(article: Article): Completable {
        return localDataSource.addReadLatorArticle(article)
    }

    override fun favoriteArticles(): Single<List<RealmArticle>> {
        return localDataSource.favoriteArticles()
    }

    override fun readLatorArticles(): Single<List<RealmArticle>> {
        return localDataSource.readLatorArticles()
    }

    override fun favoriteArticlesLiveData(): Single<LiveRealmData<RealmArticle>> {
        return localDataSource.favoriteArticlesLiveData()
    }

    override fun readLatorArticlesLiveData(): Single<LiveRealmData<RealmArticle>> {
        return localDataSource.readLatorArticlesLiveData()
    }
}