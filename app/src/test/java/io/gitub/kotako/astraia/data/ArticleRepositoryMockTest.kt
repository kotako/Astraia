package io.gitub.kotako.astraia.data

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.realm.RealmArticle
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.data.source.DataSource
import io.gitub.kotako.astraia.data.source.LiveRealmData
import io.gitub.kotako.astraia.data.source.Query
import io.gitub.kotako.astraia.data.source.local.LocalDataSource
import io.gitub.kotako.astraia.data.source.remote.RemoteDataSource
import io.gitub.kotako.astraia.data.source.remote.response.ArticleResponseEntity
import io.gitub.kotako.astraia.data.source.remote.response.AuthorResponseEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*

class ArticleRepositoryMockTest {

    private val localDataSource: DataSource = mock(LocalDataSource::class.java)
    private val remoteDataSource: DataSource = mock(RemoteDataSource::class.java)

    private fun <T> anyObject(): T {
        Mockito.anyObject<T>()
        return null as T
    }

    @Before
    fun init() {
//      Rxのスケジューラを変更
        RxJavaPlugins.setIoSchedulerHandler { _ -> Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }

//      create mock
//      RemoteDataSource
        `when`(remoteDataSource.fetchArticle(ArgumentMatchers.anyLong())).thenReturn(
                Single.just(mock(Article::class.java))
        )
        `when`(remoteDataSource.fetchArticles(anyObject())).thenReturn(
                Single.just(mock(List::class.java) as List<Article>)
        )
        `when`(remoteDataSource.fetchAuthor(ArgumentMatchers.anyLong())).thenReturn(
                Single.just(mock(List::class.java) as List<Author>)
        )
        `when`(remoteDataSource.fetchAuthors(anyObject())).thenReturn(
                Single.just(mock(List::class.java) as List<Author>)
        )
        `when`(remoteDataSource.addFavoriteArticle(anyObject())).thenReturn(
                Completable.complete()
        )
        `when`(remoteDataSource.addReadLatorArticle(anyObject())).thenReturn(
                Completable.complete()
        )
        `when`(remoteDataSource.favoriteArticles()).thenReturn(
                Single.just(mock(List::class.java) as List<RealmArticle>)
        )
        `when`(remoteDataSource.readLatorArticles()).thenReturn(
                Single.just(mock(List::class.java) as List<RealmArticle>)
        )
        `when`(remoteDataSource.favoriteArticlesLiveData()).thenReturn(
                Single.just(mock(LiveRealmData::class.java) as LiveRealmData<RealmArticle>)
        )
        `when`(remoteDataSource.readLatorArticlesLiveData()).thenReturn(
                Single.just(mock(LiveRealmData::class.java) as LiveRealmData<RealmArticle>)
        )
//      LocalDataSource
        `when`(localDataSource.fetchArticle(ArgumentMatchers.anyLong())).thenReturn(
                Single.just(mock(Article::class.java))
        )
        `when`(localDataSource.fetchArticles(anyObject())).thenReturn(
                Single.just(mock(List::class.java) as List<Article>)
        )
        `when`(localDataSource.fetchAuthor(ArgumentMatchers.anyLong())).thenReturn(
                Single.just(mock(List::class.java) as List<Author>)
        )
        `when`(localDataSource.fetchAuthors(anyObject())).thenReturn(
                Single.just(mock(List::class.java) as List<Author>)
        )
        `when`(localDataSource.addFavoriteArticle(anyObject())).thenReturn(
                Completable.complete()
        )
        `when`(localDataSource.addReadLatorArticle(anyObject())).thenReturn(
                Completable.complete()
        )
        `when`(localDataSource.favoriteArticles()).thenReturn(
                Single.just(mock(List::class.java) as List<RealmArticle>)
        )
        `when`(localDataSource.readLatorArticles()).thenReturn(
                Single.just(mock(List::class.java) as List<RealmArticle>)
        )
        `when`(localDataSource.favoriteArticlesLiveData()).thenReturn(
                Single.just(mock(LiveRealmData::class.java) as LiveRealmData<RealmArticle>)
        )
        `when`(localDataSource.readLatorArticlesLiveData()).thenReturn(
                Single.just(mock(LiveRealmData::class.java) as LiveRealmData<RealmArticle>)
        )
    }

    @After
    fun quit() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun キーワード検索で論文一覧を取得する() {
        val dummyArticle: Article = ArticleResponseEntity()
        `when`(remoteDataSource.fetchArticles(anyObject())).thenReturn(
                Single.just(listOf(dummyArticle))
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.fetchArticles(Query(keyword = "hogehoge"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(listOf(dummyArticle))

        verify(remoteDataSource).fetchArticles(anyObject())
    }

    @Test
    fun キーワードから著者を取得する() {
        val dummyAuthor: Author = AuthorResponseEntity()
        `when`(remoteDataSource.fetchAuthors(anyObject())).thenReturn(
                Single.just(listOf(dummyAuthor))
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.fetchAuthors(Query(keyword = "hogehoge"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(listOf(dummyAuthor))

        verify(remoteDataSource).fetchAuthors(anyObject())
    }

    @Test
    fun 論文IDから論文を取得() {
        val dummyArticle: Article = ArticleResponseEntity()
        `when`(remoteDataSource.fetchArticle(ArgumentMatchers.anyLong())).thenReturn(
                Single.just(dummyArticle)
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.fetchArticle(articleId = 0L)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(dummyArticle)

        verify(remoteDataSource).fetchArticle(ArgumentMatchers.anyLong())
    }

    @Test
    fun 著者IDから著者を取得() {
        val dummyAuthor: Author = AuthorResponseEntity()
        `when`(remoteDataSource.fetchAuthor(ArgumentMatchers.anyLong())).thenReturn(
                Single.just(listOf(dummyAuthor))
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.fetchAuthor(authorId = 0L)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(listOf(dummyAuthor))

        verify(remoteDataSource).fetchAuthor(ArgumentMatchers.anyLong())
    }

    @Test
    fun お気に入りの論文一覧を取得() {
        val dummyArticle = RealmArticle()
        `when`(localDataSource.favoriteArticles()).thenReturn(
                Single.just(listOf(dummyArticle))
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.favoriteArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(listOf(dummyArticle))

        verify(localDataSource).favoriteArticles()
    }

    @Test
    fun 後で読むに登録した論文一覧を取得() {
        val dummyArticle = RealmArticle()
        `when`(localDataSource.readLatorArticles()).thenReturn(
                Single.just(listOf(dummyArticle))
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.readLatorArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(listOf(dummyArticle))

        verify(localDataSource).readLatorArticles()
    }

    @Test
    fun お気に入りの論文一覧をLiveDataで取得() {
        val dummyArticle = mock(LiveRealmData::class.java)
        `when`(localDataSource.favoriteArticlesLiveData()).thenReturn(
                Single.just(dummyArticle as LiveRealmData<RealmArticle>)
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.favoriteArticlesLiveData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(dummyArticle)

        verify(localDataSource).favoriteArticlesLiveData()
    }

    @Test
    fun 後で読む論文一覧をLiveDataで取得() {
        val dummyArticle = mock(LiveRealmData::class.java)
        `when`(localDataSource.readLatorArticlesLiveData()).thenReturn(
                Single.just(dummyArticle as LiveRealmData<RealmArticle>)
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.readLatorArticlesLiveData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(dummyArticle)

        verify(localDataSource).readLatorArticlesLiveData()
    }

    @Test
    fun お気に入りの論文を追加() {
        val dummyArticle: Article = ArticleResponseEntity()
        `when`(localDataSource.addFavoriteArticle(anyObject())).thenReturn(
                Completable.complete()
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.addFavoriteArticle(dummyArticle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertComplete()

        verify(localDataSource).addFavoriteArticle(anyObject())
    }

    @Test
    fun 後で読む論文を追加() {
        val dummyArticle: Article = ArticleResponseEntity()
        `when`(localDataSource.addReadLatorArticle(anyObject())).thenReturn(
                Completable.complete()
        )
        val articleRepository = ArticleRepository(remoteDataSource, localDataSource)

        articleRepository.addReadLatorArticle(dummyArticle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .test()
                .assertComplete()

        verify(localDataSource).addReadLatorArticle(anyObject())
    }
}