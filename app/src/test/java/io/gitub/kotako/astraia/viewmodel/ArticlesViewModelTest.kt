package io.gitub.kotako.astraia.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import io.gitub.kotako.astraia.articles.ArticlesViewModel
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.data.source.remote.response.ArticleResponseEntity
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.lang.IllegalStateException

class ArticlesViewModelTest {

    private val repository: ArticleRepository = mock()
    private lateinit var viewModel: ArticlesViewModel

    private fun <T> anyObject(): T {
        Mockito.anyObject<T>()
        return null as T
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {
//      Rxのスケジューラをモック
        RxJavaPlugins.setIoSchedulerHandler { _ -> Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }

//      mock article repository
        `when`(repository.fetchArticles(anyObject())).thenReturn(
                Single.just(listOf())
        )
    }

    @After
    fun quit() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun fetchArticlesで取得した論文をリストに追加する() {
        val dummyResult = listOf(ArticleResponseEntity() as Article)
        `when`(repository.fetchArticles(anyObject())).thenReturn(
                Single.just(dummyResult)
        )
        viewModel = ArticlesViewModel(repository)

        val articlesObserver = mock<Observer<MutableList<Article>>>()
        val loadObserver = mock<Observer<Boolean>>()
        viewModel.articles.observeForever(articlesObserver)
        viewModel.isLoading.observeForever(loadObserver)

        viewModel.fetchArticles()

        verify(repository, times(1)).fetchArticles(anyObject())
        verify(articlesObserver, times(1)).onChanged(dummyResult.toMutableList())
        verify(loadObserver, times(2)).onChanged(false)
    }

    @Test
    fun 論文取得中でエラーが発生した() {
        `when`(repository.fetchArticles(anyObject())).thenReturn(
                Single.error(IllegalStateException())
        )
        viewModel = ArticlesViewModel(repository)

        val articlesObserver = mock<Observer<MutableList<Article>>>()
        val loadObserver = mock<Observer<Boolean>>()
        viewModel.articles.observeForever(articlesObserver)
        viewModel.isLoading.observeForever(loadObserver)

        viewModel.fetchArticles()

        verify(repository, times(1)).fetchArticles(anyObject())
        verify(articlesObserver, times(1)).onChanged(mutableListOf())
        verify(loadObserver, times(2)).onChanged(false)
    }
}