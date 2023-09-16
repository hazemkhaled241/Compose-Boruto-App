package com.hazem.boruto.domain.use_case.get_all_heroes

import androidx.paging.PagingData
import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHeroesUseCase @Inject constructor(
    private val repository: Repository
) {
     operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}