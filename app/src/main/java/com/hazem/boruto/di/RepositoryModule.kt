package com.hazem.boruto.di

import android.content.Context
import com.hazem.boruto.data.repository.DataStoreOperationImp
import com.hazem.boruto.data.repository.RepositoryImp
import com.hazem.boruto.domain.repository.DataStoreOperation
import com.hazem.boruto.domain.repository.Repository
import com.hazem.boruto.domain.repository.RemoteHeroes
import com.hazem.boruto.domain.use_case.UseCases
import com.hazem.boruto.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import com.hazem.boruto.domain.use_case.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataStoreOperation(@ApplicationContext context: Context): DataStoreOperation {
        return DataStoreOperationImp(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            SaveOnBoardingUseCase(repository),
            ReadOnBoardingUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRepository(dataStore: DataStoreOperation, remoteHeroes: RemoteHeroes): Repository {
        return RepositoryImp(
            remoteHeroes, dataStore
        )
    }
}