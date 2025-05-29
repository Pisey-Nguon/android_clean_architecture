package com.pisey.cleanarchitecture.domain.usecase

import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.core.toCustomResult
import com.pisey.cleanarchitecture.data.model.PostResponse
import com.pisey.cleanarchitecture.domain.repository.PostRepository


class GetPostsUseCase(private val postRepository: PostRepository) {
    suspend operator fun invoke(): CustomResult<PostResponse> {
        return try {
            postRepository.getPosts().toCustomResult()
        } catch (e: Exception) {
            CustomResult.Error(e)
        }
    }
}
