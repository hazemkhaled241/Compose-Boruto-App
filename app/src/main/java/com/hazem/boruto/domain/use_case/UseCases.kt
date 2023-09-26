package com.hazem.boruto.domain.use_case

import com.hazem.boruto.domain.use_case.get_all_heroes.GetAllHeroesUseCase
import com.hazem.boruto.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import com.hazem.boruto.domain.use_case.save_onboarding.SaveOnBoardingUseCase
import com.hazem.boruto.domain.use_case.search_for_hero.SearchForHeroUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchForHeroUseCase: SearchForHeroUseCase
)
