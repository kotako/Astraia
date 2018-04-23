package io.gitub.kotako.astraia.di

import dagger.Module
import dagger.Provides
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.data.source.local.LocalDataSource
import io.gitub.kotako.astraia.data.source.remote.RemoteDataSource
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object RepositoryModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://ci.nii.ac.jp")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    fun provideRemoteDataSource(retrofit: Retrofit): RemoteDataSource = RemoteDataSource(retrofit)

    @Provides
    fun provideRealmConfig(): RealmConfiguration =
            RealmConfiguration.Builder()
                    .name("kotako.astraia")
                    .deleteRealmIfMigrationNeeded()
                    .build()

    @Provides
    fun provideRealm(realmConfig: RealmConfiguration): Realm = Realm.getInstance(realmConfig)

    @Provides
    fun provideLocalDataSource(realm: Realm): LocalDataSource = LocalDataSource(realm)

    @Provides
    fun provideArticleRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): ArticleRepository = ArticleRepository(remoteDataSource, localDataSource)
}