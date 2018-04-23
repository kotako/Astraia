package io.gitub.kotako.astraia.articles

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.gitub.kotako.astraia.data.Entity.Article
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.util.defaultErrorHandler
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference
import javax.inject.Inject

class ArticleItemViewModel @Inject constructor(
        private val articleRepository: ArticleRepository
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var navigator: WeakReference<ArticleItemNavigator>? = null

    val isReadLaterAdded: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply { postValue(false) }
    }

    fun articleClicked() {
        navigator?.get()?.onStartArticleDetail()
    }

    fun readLaterClicked(article: Article) {
        compositeDisposable.add((articleRepository.addReadLatorArticle(article)
                .subscribe(
                        { isReadLaterAdded.value = true },
                        defaultErrorHandler()
                )))
    }

    fun setNavigator(navigator: ArticleItemNavigator) {
        this.navigator = WeakReference(navigator)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        navigator = null
        super.onCleared()
    }
}