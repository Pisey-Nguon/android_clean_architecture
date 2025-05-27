package com.pisey.cleanarchitecture.domain.usecase

import com.pisey.cleanarchitecture.domain.repository.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUsers()
}