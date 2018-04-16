package io.gitub.kotako.astraia

import io.gitub.kotako.astraia.articles.ArticlesViewModel
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Query
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.data.source.remote.RemoteDataSource
import io.gitub.kotako.astraia.data.source.remote.response.ArticleResponseEntity
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(RobolectricTestRunner::class)
class ArticlesViewModelTest {

    private lateinit var repository: ArticleRepository
    private lateinit var viewModel: ArticlesViewModel
    private val query: Query = Query(keyword = "android", startIndex = 1)

    @Before
    fun init() {
        RxJavaPlugins.setIoSchedulerHandler { _ -> Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }

        repository = mock(ArticleRepository::class.java)
        `when`(repository.fetchArticles(query)).thenReturn(
                Single.just(listOf(ArticleResponseEntity() as Article))
        )
    }

    @After
    fun quit() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun 論文一覧のモックを取得する() {
        viewModel = ArticlesViewModel(repository)
        viewModel.query = query

        viewModel.fetchArticles()

        print(viewModel.articles)
        assertEquals(viewModel.articles, listOf(ArticleResponseEntity() as Article))
        assert(viewModel.articles.size == 1)
    }

    @Test
    fun 実際にAPIから論文一覧を取得する() {
        val retrofit = Retrofit.Builder()
                .client(OkHttpClient())
                .baseUrl("http://ci.nii.ac.jp")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        repository = ArticleRepository(RemoteDataSource(retrofit))
        viewModel = ArticlesViewModel(repository)
        viewModel.query = query

        viewModel.fetchArticles()

        print(viewModel.articles)
        assert(viewModel.articles.isNotEmpty())
    }

    @Test
    fun 論文一覧取得中にネットワークエラーが発生したら知らせる() {
    }
}