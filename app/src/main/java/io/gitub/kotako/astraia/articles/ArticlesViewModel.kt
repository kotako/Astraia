package io.gitub.kotako.astraia.articles

import android.arch.lifecycle.ViewModel
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.Query
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.util.defaultErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
        private val repository: ArticleRepository
): ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var articles: MutableList<Article> = mutableListOf()
    var isLoading: Boolean = false
    var query: Query = Query(keyword = "")

    fun init() {
        articles = mutableListOf()
        isLoading = false
    }

    fun fetchArticles() {
        if (compositeDisposable.size() > 0 || isLoading) return
        query.startIndex = articles.size + 1
        compositeDisposable.add(repository.fetchArticles(query)
                .doOnSubscribe { isLoading = true }
                .doFinally { isLoading = false }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: List<Article> -> articles.addAll(t) }, defaultErrorHandler())
        )
    }

    fun startArticleDetailActivity(){}

    fun addFavorite() {}

    fun addReadLator() {}

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}