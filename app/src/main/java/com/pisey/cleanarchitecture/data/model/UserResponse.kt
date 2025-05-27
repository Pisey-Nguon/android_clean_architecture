package com.pisey.cleanarchitecture.data.model

import com.pisey.cleanarchitecture.data.remote.dto.UserDto

data class UserResponse(
    val users: List<UserDto>
)
