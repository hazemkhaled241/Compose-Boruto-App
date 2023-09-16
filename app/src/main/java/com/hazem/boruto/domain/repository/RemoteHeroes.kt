package com.hazem.boruto.domain.repository

import androidx.paging.PagingData
import com.hazem.boruto.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteHeroes {
    suspend fun getAllHeroes():Flow<PagingData<Hero>>
    suspend fun searchForHero():Flow<PagingData<Hero>>
}