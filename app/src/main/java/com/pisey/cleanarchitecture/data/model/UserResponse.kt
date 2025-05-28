package com.pisey.cleanarchitecture.data.model

import com.pisey.cleanarchitecture.domain.model.User

data class UserResponse(
    val users: List<User>
)
