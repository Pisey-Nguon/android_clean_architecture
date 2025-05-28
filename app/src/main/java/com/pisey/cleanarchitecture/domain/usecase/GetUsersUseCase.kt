package com.pisey.cleanarchitecture.domain.usecase

import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.core.toCustomResult
import com.pisey.cleanarchitecture.data.model.UserResponse
import com.pisey.cleanarchitecture.domain.repository.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): CustomResult<UserResponse> {
        return try {
            repository.getUsers().toCustomResult()
        } catch (e: Exception) {
            CustomResult.Error(e)
        }
    }
}