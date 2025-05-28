package com.pisey.cleanarchitecture.data.repository

import com.pisey.cleanarchitecture.data.model.UserResponse
import com.pisey.cleanarchitecture.data.remote.ApiService
import com.pisey.cleanarchitecture.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
    override suspend fun getUsers(): UserResponse {
        return apiService.getUsers()
    }
}