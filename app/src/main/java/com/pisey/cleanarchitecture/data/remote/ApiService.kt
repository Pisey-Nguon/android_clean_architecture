package com.pisey.cleanarchitecture.data.remote

import com.pisey.cleanarchitecture.data.model.PostResponse
import com.pisey.cleanarchitecture.data.model.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): UserResponse

    @GET("posts")
    suspend fun getPosts(): PostResponse

}
