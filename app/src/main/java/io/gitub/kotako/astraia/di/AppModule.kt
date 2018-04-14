package io.gitub.kotako.astraia.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.gitub.kotako.astraia.data.source.ArticleRepository
import io.gitub.kotako.astraia.data.source.DataSource
import io.gitub.kotako.astraia.data.source.remote.RemoteDataSource
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
    fun provideRemoteDataSource(retrofit: Retrofit): DataSource = RemoteDataSource(retrofit)

    @Provides
    @Singleton
    fun provideArticleRepository(remoteDataSource: DataSource) = ArticleRepository(remoteDataSource)
}