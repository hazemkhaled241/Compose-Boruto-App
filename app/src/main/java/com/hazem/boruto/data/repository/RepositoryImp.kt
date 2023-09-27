package com.hazem.boruto.data.repository

import androidx.paging.PagingData
import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.domain.repository.DataStoreOperation
import com.hazem.boruto.domain.repository.LocalDataSource
import com.hazem.boruto.domain.repository.RemoteHeroes
import com.hazem.boruto.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val remoteHeroes: RemoteHeroes, private val dataStore: DataStoreOperation,
    private val localDataSource: LocalDataSource
) : Repository {
    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteHeroes.getAllHeroes()
    }

    override fun searchForHeroes(query: String): Flow<PagingData<Hero>> {
        return remoteHeroes.searchForHero(query = query)
    }

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return localDataSource.getSelectedHero(heroId = heroId)
    }


    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed)
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}