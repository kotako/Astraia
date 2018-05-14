package io.gitub.kotako.astraia.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.gitub.kotako.astraia.detail.ArticleDetailActivity
import io.gitub.kotako.astraia.detail.ArticleDetailViewModel

@Module
interface ArticleDetailActivityModule {
    @ContributesAndroidInjector
    fun contributeArticleDetailActivity(): ArticleDetailActivity

    @Binds
    @IntoMap
    @ViewModelKey(ArticleDetailViewModel::class)
    fun bindArticleDetailViewModel(articleDetailViewModel: ArticleDetailViewModel): ViewModel
}