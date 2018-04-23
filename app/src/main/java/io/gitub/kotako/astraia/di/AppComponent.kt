package io.gitub.kotako.astraia.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.gitub.kotako.astraia.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    RepositoryModule::class,
    ArticlesActivityModule::class
])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun appModule(appModule: AppModule): Builder
        fun repository(repository: RepositoryModule): Builder
        fun build(): AppComponent
    }

    override fun inject(app: App)
}