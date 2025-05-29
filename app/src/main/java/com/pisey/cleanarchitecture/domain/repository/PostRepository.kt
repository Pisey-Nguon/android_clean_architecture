package com.pisey.cleanarchitecture.domain.repository

import com.pisey.cleanarchitecture.data.model.PostResponse


interface PostRepository {
    suspend fun getPosts(): PostResponse
}
