package com.hazem.boruto.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.domain.use_case.UseCases
import com.hazem.boruto.utils.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
private val _selectedHero: MutableState<Hero?> = mutableStateOf(null)
    val selectedHero:State<Hero?> = _selectedHero
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
           _selectedHero.value = heroId?.let {
               useCases.getSelectedHeroUseCase(it)
           }
            _selectedHero.value?.name?.let{
                Log.d("Hero",it)
            }
        }
    }
}