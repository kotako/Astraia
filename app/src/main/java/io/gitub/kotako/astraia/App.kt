package io.gitub.kotako.astraia

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.gitub.kotako.astraia.di.AppModule
import io.gitub.kotako.astraia.di.DaggerAppComponent
import io.realm.Realm

class App: DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this)
                .appModule(AppModule)
                .build()
    }
}