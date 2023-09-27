package com.hazem.boruto.domain.use_case.get_selected_hero

import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.domain.repository.Repository
import javax.inject.Inject

class GetSelectedHeroUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId = heroId)
    }
}