package io.gitub.kotako.astraia

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.data.source.DataSource
import io.gitub.kotako.astraia.data.source.remote.RemoteDataSource
import io.gitub.kotako.astraia.data.source.remote.response.ArticleResponseEntity
import io.gitub.kotako.astraia.data.source.remote.response.AuthorResponseEntity
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch

class ArticleRepositoryTest {

    private lateinit var articleRepository: DataSource
    private lateinit var latch: CountDownLatch

    @Before
    fun init () {
        val retrofit = Retrofit.Builder()
                .client(OkHttpClient())
                .baseUrl("http://ci.nii.ac.jp")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        articleRepository = ArticleRepository(RemoteDataSource(retrofit))
        latch = CountDownLatch(1)
    }

    @Test
    fun キーワードから論文を取得する() {
        articleRepository.fetchArticles(keyword = "android")
                .subscribeOn(Schedulers.io())
                .subscribe { articles: List<Article>?, t: Throwable? ->
                    articles?.run {
                        print(articles)
                        assert(articles.isNotEmpty())
                    }
                    t?.run { throw t }
                    latch.countDown()
                }
        latch.await()
    }

    @Test
    fun キーワードから著者を取得する() {
        articleRepository.fetchAuthors(keyword = "鈴木")
                .subscribeOn(Schedulers.io())
                .subscribe { authors: List<Author>?, t: Throwable? ->
                    authors?.run {
                        print(authors)
                        assert(authors.isNotEmpty())
                    }
                    t?.run { throw t }
                    latch.countDown()
                }
        latch.await()
    }

    @Test
    fun 論文IDから論文を取得() {
        articleRepository.fetchArticle(articleId = 40021450187)
                .subscribeOn(Schedulers.io())
                .subscribe { article: Article?, t: Throwable? ->
                    article?.run {
                        print(article)
                        assert(article != ArticleResponseEntity())
                    }
                    t?.run { throw t }
                    latch.countDown()
                }
        latch.await()
    }

    @Test
    fun 著者IDから著者を取得() {
        articleRepository.fetchAuthor(authorId = 9000006028671)
                .subscribeOn(Schedulers.io())
                .subscribe { author: List<Author>?, t: Throwable? ->
                    author?.run {
                        print(author)
                        assert(author.first() != AuthorResponseEntity())
                    }
                    t?.run { throw t }
                    latch.countDown()
                }
        latch.await()
    }
}