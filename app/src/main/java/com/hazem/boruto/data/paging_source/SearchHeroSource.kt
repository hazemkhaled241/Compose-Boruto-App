package com.hazem.boruto.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hazem.boruto.data.remote.BorutoApi
import com.hazem.boruto.domain.model.Hero
import javax.inject.Inject

class SearchHeroSource @Inject constructor(
    private val borutoApi: BorutoApi,
    private val query: String
) : PagingSource<Int, Hero>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val response = borutoApi.searchForHeroes(query)
            val heroes = response.heroes
            if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    prevKey = response.prevPage,
                    nextKey = response.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
       return state.anchorPosition
    }
}