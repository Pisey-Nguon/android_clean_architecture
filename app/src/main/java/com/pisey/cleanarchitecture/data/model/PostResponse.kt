package com.pisey.cleanarchitecture.data.model

import com.pisey.cleanarchitecture.data.remote.dto.PostDto

data class PostResponse(
    val posts: List<PostDto>
)