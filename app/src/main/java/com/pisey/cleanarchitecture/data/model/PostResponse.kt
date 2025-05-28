package com.pisey.cleanarchitecture.data.model

import com.pisey.cleanarchitecture.domain.model.Post

data class PostResponse(
    val posts: List<Post>
)