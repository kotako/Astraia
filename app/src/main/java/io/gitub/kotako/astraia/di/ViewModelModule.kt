package io.gitub.kotako.astraia.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelProvider.AndroidViewModelFactory): ViewModelProvider.Factory
}