package com.pisey.cleanarchitecture.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.pisey.cleanarchitecture.domain.model.User

data class UserDto(
    val id: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("email") val email: String
) {
    fun toUser(): User = User(id, firstName, lastName, email)
}