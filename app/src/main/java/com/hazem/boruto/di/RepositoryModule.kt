package com.hazem.boruto.di

import android.content.Context
import com.hazem.boruto.data.repository.DataStoreOperationImp
import com.hazem.boruto.domain.repository.DataStoreOperation
import com.hazem.boruto.domain.repository.DataStoreRepository
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
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreOperation {
        return DataStoreOperationImp(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(dataStoreRepository: DataStoreRepository): UseCases {
        return UseCases(
            SaveOnBoardingUseCase(dataStoreRepository),
            ReadOnBoardingUseCase(dataStoreRepository)
        )
    }
}