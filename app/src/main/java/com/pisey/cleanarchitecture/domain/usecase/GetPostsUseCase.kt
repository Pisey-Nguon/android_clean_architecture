package com.pisey.cleanarchitecture.domain.usecase

import com.pisey.cleanarchitecture.domain.repository.PostRepository


class GetPostsUseCase(private val postRepository: PostRepository) {
    suspend operator fun invoke() = postRepository.getPosts()
}
