package com.hazem.boruto.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hazem.boruto.data.local.HeroDataBase
import com.hazem.boruto.data.paging_source.HeroRemoteMediator
import com.hazem.boruto.data.paging_source.SearchHeroSource
import com.hazem.boruto.data.remote.BorutoApi
import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.domain.repository.RemoteHeroes
import com.hazem.boruto.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RemoteHeroesImp @Inject constructor(
    private val heroDataBase: HeroDataBase,
    private val borutoApi: BorutoApi
) : RemoteHeroes {

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDataBase.heroDao().getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(borutoApi = borutoApi, heroDataBase = heroDataBase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchForHero(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = { SearchHeroSource(borutoApi = borutoApi, query = query) }
        ).flow
    }
}