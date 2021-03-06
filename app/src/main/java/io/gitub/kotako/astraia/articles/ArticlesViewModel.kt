package io.gitub.kotako.astraia.articles

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.Query
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.util.defaultErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
        private val repository: ArticleRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var navigator: ArticlesNavigator? = null

    var query: Query = Query(keyword = "")
    val articles = MutableLiveData<MutableList<Article>>().apply { postValue(mutableListOf()) }
    val isLoading = MutableLiveData<Boolean>().apply { postValue(false) }

    fun setNavigator(articlesNavigator: ArticlesNavigator) {
        navigator = articlesNavigator
    }

    fun fetchArticles() {
        if (compositeDisposable.isDisposed || isLoading.value == true) return
        query.startIndex = (articles.value?.size ?: 0) + 1
        compositeDisposable.add(repository.fetchArticles(query)
                .doOnSubscribe { isLoading.postValue(true) }
                .doFinally { isLoading.postValue(false) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .subscribe({ t: List<Article> -> articles.postValue(articles.value?.plus(t)?.toMutableList()) }, defaultErrorHandler())
        )
    }

    fun refreshArticles() {
        articles.postValue(mutableListOf())
        query = Query(keyword = query.keyword)
        fetchArticles()
    }

    fun searchArticles(keyword: String) {
        articles.postValue(mutableListOf())
        query = Query(keyword = keyword)
        fetchArticles()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        navigator = null
        super.onCleared()
    }
}