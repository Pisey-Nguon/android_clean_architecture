package com.pisey.cleanarchitecture.domain.repository

import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.domain.model.Post


interface PostRepository {
    suspend fun getPosts(): CustomResult<List<Post>>
}
