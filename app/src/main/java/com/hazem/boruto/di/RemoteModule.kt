package com.hazem.boruto.di

import com.hazem.boruto.data.local.HeroDataBase
import com.hazem.boruto.data.remote.BorutoApi
import com.hazem.boruto.data.repository.RemoteHeroesImp
import com.hazem.boruto.domain.repository.RemoteHeroes
import com.hazem.boruto.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(
            15, TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideBorutoApi(okHttpClient: OkHttpClient): BorutoApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(BorutoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteHeroes(heroDataBase: HeroDataBase, borutoApi: BorutoApi): RemoteHeroes {
        return RemoteHeroesImp(
            heroDataBase = heroDataBase,
            borutoApi = borutoApi
        )
    }
}