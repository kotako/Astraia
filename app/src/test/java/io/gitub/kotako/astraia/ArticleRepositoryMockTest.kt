package io.gitub.kotako.astraia

import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Entity.Author
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.data.source.remote.response.ArticleColumnResponseEntity
import io.gitub.kotako.astraia.data.source.remote.response.ArticleResponseEntity
import io.gitub.kotako.astraia.data.source.remote.response.AuthorColumnResponseEntity
import io.gitub.kotako.astraia.data.source.remote.response.AuthorResponseEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.Before

import org.mockito.Mockito.*
import java.util.concurrent.CountDownLatch

class ArticleRepositoryMockTest {

    private lateinit var articleRepository: ArticleRepository
    private lateinit var latch: CountDownLatch

    @Before
    fun init() {
        latch = CountDownLatch(1)
//      Mockをつくる
        articleRepository = mock(ArticleRepository::class.java)

        `when`(articleRepository.fetchArticles(keyword = "android")).thenReturn(
                Observable.just(listOf(ArticleColumnResponseEntity() as Article))
                        .singleOrError()
        )
        `when`(articleRepository.fetchAuthors(keyword = "android")).thenReturn(
                Observable.just(listOf(AuthorColumnResponseEntity() as Author))
                        .singleOrError()
        )
        `when`(articleRepository.fetchArticle(articleId = 0L)).thenReturn(
                Observable.just(ArticleResponseEntity() as Article)
                        .singleOrError()
        )
        `when`(articleRepository.fetchAuthor(authorId = 0L)).thenReturn(
                Observable.just(listOf(AuthorResponseEntity() as Author))
                        .singleOrError()
        )
    }

    @Test
    fun キーワードから論文を取得する() {
        articleRepository.fetchArticles(keyword = "android")
                .subscribeOn(Schedulers.io())
                .subscribe { articles: List<Article>?, t: Throwable? ->
                    articles?.run {
                        print(articles.first())
                        assert(articles.isNotEmpty())
                        assert(articles.size == 1)
                        assert(articles.contains(ArticleColumnResponseEntity()))
                        latch.countDown()
                    }
                    t?.run { doThrow(t) }
                }
        latch.await()
    }

    @Test
    fun キーワードから著者を取得する() {
        articleRepository.fetchAuthors(keyword = "android")
                .subscribeOn(Schedulers.io())
                .subscribe { authors: List<Author>?, t: Throwable? ->
                    authors?.run {
                        print(authors.first())
                        assert(authors.isNotEmpty())
                        assert(authors.size == 1)
                        assert(authors.contains(AuthorColumnResponseEntity()))
                        latch.countDown()
                    }
                    t?.run { doThrow(t) }
                }
        latch.await()
    }

    @Test
    fun 論文IDから論文を取得() {
        articleRepository.fetchArticle(articleId = 0L)
                .subscribeOn(Schedulers.io())
                .subscribe { article: Article?, t: Throwable? ->
                    article?.run {
                        print(article)
                        assert(article == ArticleResponseEntity())
                        latch.countDown()
                    }
                    t?.run { doThrow(t) }
                }
        latch.await()
    }

    @Test
    fun 著者IDから著者を取得() {
        articleRepository.fetchAuthor(authorId = 0L)
                .subscribeOn(Schedulers.io())
                .subscribe { author: List<Author>?, t: Throwable? ->
                    author?.run {
                        print(author)
                        assert(author.isNotEmpty())
                        latch.countDown()
                    }
                    t?.run { doThrow(t) }
                }
        latch.await()
    }
}