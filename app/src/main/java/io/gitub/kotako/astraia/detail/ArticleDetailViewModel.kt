package io.gitub.kotako.astraia.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.realm.RealmArticle
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.util.defaultErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleDetailViewModel @Inject constructor(
        private val repository: ArticleRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var navigator: ArticleDetailNavigator? = null

    val article = MutableLiveData<Article>().apply { postValue(null) }
    val readLater = MutableLiveData<Boolean>().apply { postValue(false) }
    val favorite = MutableLiveData<Boolean>().apply { postValue(false) }
    val isLoading = MutableLiveData<Boolean>().apply { postValue(true) }
    val isExpand = MutableLiveData<Boolean>().apply { postValue(false) }

    fun setArticle(article: Article) {
        if (article.id == null) return
        isLoading.postValue(true)
        repository.fetchArticle(articleId = article.id!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { isLoading.postValue(false) }
                .retry(2)
                .subscribe({ t: Article ->
                    this.article.postValue(t)
                }, defaultErrorHandler())

        repository.readLatorArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: List<RealmArticle> ->
                    this.readLater.postValue(!t.none { it.id == article.id })
                }, defaultErrorHandler())

        repository.favoriteArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: List<RealmArticle> ->
                    this.favorite.postValue(!t.none { it.id == article.id })
                }, defaultErrorHandler())
    }

    fun setNavigator(navigator: ArticleDetailNavigator) {
        this.navigator = navigator
    }

    fun onFavoriteClick(article: Article) {
        compositeDisposable.add(repository.addFavoriteArticle(article)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    favorite.postValue(true)
                }, defaultErrorHandler())
        )
    }

    fun onReadLaterClick(article: Article) {
        compositeDisposable.add(repository.addReadLatorArticle(article)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    readLater.postValue(true)
                }, defaultErrorHandler())
        )
    }

    fun expandDescription() {
        isExpand.postValue(isExpand.value?.not())
    }

    fun onShareClick(article: Article) {
        navigator?.openBrowser()
    }

    fun onLinkClick(url: String) {
        navigator?.openBrowser()
    }

    fun onAuthorClick() {
        navigator?.searchAuthor()
    }

    override fun onCleared() {
        navigator = null
        compositeDisposable.clear()
        super.onCleared()
    }
}