package com.hazem.boruto.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.hazem.boruto.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
):ViewModel() {
val getAllHeroes=useCases.getAllHeroesUseCase()
}