package com.pisey.cleanarchitecture.domain.repository

import com.pisey.cleanarchitecture.data.model.UserResponse

interface UserRepository {
    suspend fun getUsers(): UserResponse
}