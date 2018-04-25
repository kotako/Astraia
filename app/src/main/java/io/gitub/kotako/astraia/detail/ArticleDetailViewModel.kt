package io.gitub.kotako.astraia.detail

import android.arch.lifecycle.ViewModel
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.util.defaultErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleDetailViewModel @Inject constructor(
        private val repository: ArticleRepository
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var navigator: ArticleDetailNavigator? = null

    fun setNavigator(navigator: ArticleDetailNavigator) {
        this.navigator = navigator
    }

    fun onFavoriteClick(article: Article) {
        compositeDisposable.add(repository.addFavoriteArticle(article)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, defaultErrorHandler())
        )
    }

    fun onReadLaterClick(article: Article) {
        compositeDisposable.add(repository.addReadLatorArticle(article)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, defaultErrorHandler())
        )
    }

    fun onShareClick() {
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