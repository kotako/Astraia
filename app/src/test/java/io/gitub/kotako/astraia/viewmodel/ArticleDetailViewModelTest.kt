package io.gitub.kotako.astraia.viewmodel

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.detail.ArticleDetailViewModel
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ArticleDetailViewModelTest {

    private val repository = mock<ArticleRepository>()
    private val dummyArticle = mock<Article>()

    private fun <T> anyObject(): T {
        Mockito.anyObject<T>()
        return null as T
    }

    @Before
    fun init() {
        RxJavaPlugins.setIoSchedulerHandler { _ -> Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @After
    fun quit() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun 論文をお気に入りに追加する() {
        whenever(repository.addFavoriteArticle(anyObject())).thenReturn(
                Completable.complete()
        )
        val viewModel = ArticleDetailViewModel(repository)
        viewModel.onFavoriteClick(dummyArticle)

        verify(repository).addFavoriteArticle(anyObject())
    }

    @Test
    fun 論文を後で読むリストに追加する() {
        whenever(repository.addReadLatorArticle(anyObject())).thenReturn(
                Completable.complete()
        )
        val viewModel = ArticleDetailViewModel(repository)
        viewModel.onReadLaterClick(dummyArticle)

        verify(repository).addReadLatorArticle(anyObject())
    }

    @Test
    fun 論文をお気に入りに追加できなかった() {
        whenever(repository.addFavoriteArticle(anyObject())).thenReturn(
                Completable.error(IllegalStateException())
        )
        val viewModel = ArticleDetailViewModel(repository)
        viewModel.onFavoriteClick(dummyArticle)

        verify(repository).addFavoriteArticle(anyObject())
    }

    @Test
    fun リンクを押すとブラウザへ遷移() {}

    @Test
    fun 著者を検索する() {}
}