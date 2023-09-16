package com.hazem.boruto.domain.use_case.read_onboarding

import com.hazem.boruto.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoardingUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}