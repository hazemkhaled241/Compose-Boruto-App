package com.hazem.boruto.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hazem.boruto.data.local.HeroDataBase
import com.hazem.boruto.data.remote.BorutoApi
import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.domain.model.HeroRemoteKey
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val heroDataBase: HeroDataBase
) : RemoteMediator<Int, Hero>() {
    private val heroDao = heroDataBase.heroDao()
    private val remoteKeyDao = heroDataBase.heroRemoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = remoteKeyDao.getRemoteKey(id = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440
        val differentInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (differentInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    getRemoteKeyClosestToCurrentPosition(state)?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                }
            }

            val response = borutoApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {
                heroDataBase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        remoteKeyDao.deleteAllRemoteKeys()
                    }
                    val keys = response.heroes.map { hero ->
                        HeroRemoteKey(
                            id = hero.id,
                            prevPage = response.prevPage,
                            nextPage = response.nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    remoteKeyDao.addAllRemoteKeys(keys)
                    heroDao.addHeroes(response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKey? {

        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                remoteKeyDao.getRemoteKey(hero.id)
            }
    }


    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                remoteKeyDao.getRemoteKey(hero.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeyDao.getRemoteKey(id)
            }

        }
    }

}
