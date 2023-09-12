package com.hazem.boruto.domain.use_case.save_onboarding

import com.hazem.boruto.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveOnBoardingUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(completed: Boolean) {
        dataStoreRepository.saveOnBoardingState(completed)

    }
}