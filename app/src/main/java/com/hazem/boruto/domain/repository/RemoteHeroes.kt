package com.hazem.boruto.domain.repository

import androidx.paging.PagingData
import com.hazem.boruto.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteHeroes {
     fun getAllHeroes():Flow<PagingData<Hero>>
     fun searchForHero(query:String):Flow<PagingData<Hero>>
}