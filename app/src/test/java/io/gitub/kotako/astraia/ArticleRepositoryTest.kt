package io.gitub.kotako.astraia

import io.gitub.kotako.astraia.data.Article
import io.gitub.kotako.astraia.data.ArticleColumn
import io.gitub.kotako.astraia.data.Author
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.Before

import org.mockito.Mockito.*
import java.util.concurrent.CountDownLatch

class ArticleRepositoryTest {

    private lateinit var articleRepository: ArticleRepository
    private lateinit var latch: CountDownLatch

    @Before
    fun init() {
        latch = CountDownLatch(1)
//      Mockをつくる
        articleRepository = mock(ArticleRepository::class.java)

        `when`(articleRepository.fetchArticles(keyword = "android")).thenReturn(
                Observable.just(listOf(ArticleColumn()))
                        .singleOrError()
        )
        `when`(articleRepository.fetchAuthors(keyword = "android")).thenReturn(
                Observable.just(listOf(Author()))
                        .singleOrError()
        )
        `when`(articleRepository.fetchArticle(articleId = 0L)).thenReturn(
                Observable.just(Article())
                        .singleOrError()
        )
        `when`(articleRepository.fetchAuthor(authorId = 0L)).thenReturn(
                Observable.just(Author())
                        .singleOrError()
        )
    }

    @Test
    fun キーワードから論文を取得する() {
        articleRepository.fetchArticles(keyword = "android")
                .subscribeOn(Schedulers.io())
                .subscribe { articles: List<ArticleColumn>?, t: Throwable? ->
                    articles?.run {
                        print(articles.first())
                        assert(articles.isNotEmpty())
                        assert(articles.size == 1)
                        assert(articles.contains(ArticleColumn()))
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
                        assert(authors.contains(Author()))
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
                        assert(article == Article())
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
                .subscribe { author: Author?, t: Throwable? ->
                    author?.run {
                        print(author)
                        assert(author == Author())
                        latch.countDown()
                    }
                    t?.run { doThrow(t) }
                }
        latch.await()
    }
}