package com.pisey.cleanarchitecture.domain.repository

import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.data.model.PostResponse


interface PostRepository {
    suspend fun getPosts(): CustomResult<PostResponse>
}
