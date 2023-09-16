package com.hazem.boruto.domain.use_case.save_onboarding

import com.hazem.boruto.domain.repository.Repository
import javax.inject.Inject

class SaveOnBoardingUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)

    }
}