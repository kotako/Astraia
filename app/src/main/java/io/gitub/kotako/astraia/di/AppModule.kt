package io.gitub.kotako.astraia.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.gitub.kotako.astraia.articles.ArticleItemViewModel
import io.gitub.kotako.astraia.articles.ArticlesViewModel
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.data.source.DataSource
import io.gitub.kotako.astraia.data.source.local.LocalDataSource
import io.gitub.kotako.astraia.data.source.remote.RemoteDataSource
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://ci.nii.ac.jp")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(retrofit: Retrofit): RemoteDataSource = RemoteDataSource(retrofit)

    @Provides
    @Singleton
    fun provideArticleRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): ArticleRepository = ArticleRepository(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideRealmConfig(): RealmConfiguration =
            RealmConfiguration.Builder()
                    .name("kotako.astraia")
                    .deleteRealmIfMigrationNeeded()
                    .build()

    @Provides
    @Singleton
    fun provideRealm(realmConfig: RealmConfiguration): Realm = Realm.getInstance(realmConfig)

    @Provides
    @Singleton
    fun provideLocalDataSource(realm: Realm): LocalDataSource = LocalDataSource(realm)

    @Provides
    @Singleton
    fun provideArticlesViewModel(repository: ArticleRepository): ArticlesViewModel = ArticlesViewModel(repository)
}