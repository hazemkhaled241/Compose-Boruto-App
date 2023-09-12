package com.hazem.boruto.data.repository

import com.hazem.boruto.domain.repository.DataStoreOperation
import com.hazem.boruto.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImp @Inject constructor(
    private val dataStore:DataStoreOperation
): DataStoreRepository {
    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed)
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}