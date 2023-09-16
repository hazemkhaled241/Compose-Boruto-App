package com.hazem.boruto.domain.repository

import androidx.paging.PagingData
import com.hazem.boruto.domain.model.Hero
import kotlinx.coroutines.flow.Flow


interface Repository {
     fun getAllHeroes():Flow<PagingData<Hero>>
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}