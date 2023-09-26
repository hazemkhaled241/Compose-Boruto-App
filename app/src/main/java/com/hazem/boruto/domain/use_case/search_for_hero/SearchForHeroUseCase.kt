package com.hazem.boruto.domain.use_case.search_for_hero

import androidx.paging.PagingData
import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchForHeroUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchForHeroes(query = query)
    }
}