package com.hazem.boruto.di

import android.content.Context
import com.hazem.boruto.data.local.HeroDataBase
import com.hazem.boruto.data.repository.DataStoreOperationImp
import com.hazem.boruto.data.repository.LocalDataSourceImp
import com.hazem.boruto.data.repository.RepositoryImp
import com.hazem.boruto.domain.repository.DataStoreOperation
import com.hazem.boruto.domain.repository.LocalDataSource
import com.hazem.boruto.domain.repository.Repository
import com.hazem.boruto.domain.repository.RemoteHeroes
import com.hazem.boruto.domain.use_case.UseCases
import com.hazem.boruto.domain.use_case.get_all_heroes.GetAllHeroesUseCase
import com.hazem.boruto.domain.use_case.get_selected_hero.GetSelectedHeroUseCase
import com.hazem.boruto.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import com.hazem.boruto.domain.use_case.save_onboarding.SaveOnBoardingUseCase
import com.hazem.boruto.domain.use_case.search_for_hero.SearchForHeroUseCase
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
            ReadOnBoardingUseCase(repository),
            GetAllHeroesUseCase(repository),
            SearchForHeroUseCase(repository),
            GetSelectedHeroUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRepository(dataStore: DataStoreOperation, remoteHeroes: RemoteHeroes,localDataSource: LocalDataSource): Repository {
        return RepositoryImp(
            remoteHeroes, dataStore,localDataSource
        )
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(heroDataBase: HeroDataBase): LocalDataSource {
        return LocalDataSourceImp(heroDataBase)
    }

}