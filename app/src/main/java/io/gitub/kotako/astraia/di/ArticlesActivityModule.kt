package io.gitub.kotako.astraia.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.gitub.kotako.astraia.articles.ArticlesActivity
import io.gitub.kotako.astraia.articles.ArticlesFragment
import io.gitub.kotako.astraia.articles.ArticlesViewModel

@Module
interface ArticlesActivityModule {
    @ContributesAndroidInjector
    fun contributeArticlesActivity(): ArticlesActivity

    @ContributesAndroidInjector
    fun contributeArticlesFragment(): ArticlesFragment

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    fun bindArticlesViewModel(articlesViewModel: ArticlesViewModel): ViewModel
}