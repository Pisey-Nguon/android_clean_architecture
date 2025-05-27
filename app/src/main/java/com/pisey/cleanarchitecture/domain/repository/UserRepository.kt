package com.pisey.cleanarchitecture.domain.repository

import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.domain.model.User

interface UserRepository {
    suspend fun getUsers(): CustomResult<List<User>>
}