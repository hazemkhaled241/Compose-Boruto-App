package com.hazem.boruto.domain.use_case.read_onboarding

import com.hazem.boruto.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoardingUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {

    operator fun invoke(): Flow<Boolean> {
        return dataStoreRepository.readOnBoardingState()
    }
}