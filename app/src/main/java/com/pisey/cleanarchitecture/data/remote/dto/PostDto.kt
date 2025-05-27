package com.pisey.cleanarchitecture.data.remote.dto

import com.pisey.cleanarchitecture.domain.model.Post

data class PostDto(
    val id: Int,
    val title: String,
    val body: String
) {
    fun toPost() = Post(id, title, body)
}
