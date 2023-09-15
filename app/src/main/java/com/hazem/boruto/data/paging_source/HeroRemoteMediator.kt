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
):RemoteMediator<Int,Hero>() {
    private val heroDao = heroDataBase.heroDao()
    private val remoteKeyDao = heroDataBase.heroRemoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return  try {
            val response = borutoApi.getAllHeroes()
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
                            nextPage = response.nextPage
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

}
