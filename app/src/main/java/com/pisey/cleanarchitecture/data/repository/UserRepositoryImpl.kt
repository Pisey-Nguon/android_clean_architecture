package com.pisey.cleanarchitecture.data.repository

import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.data.remote.ApiService
import com.pisey.cleanarchitecture.domain.model.User
import com.pisey.cleanarchitecture.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: ApiService): UserRepository {
    override suspend fun getUsers(): CustomResult<List<User>> {
        return try {
            CustomResult.Success(apiService.getUsers().users.map { it.toUser() })
        }catch (e: Exception) {
            CustomResult.Error(e)
        }
    }
}