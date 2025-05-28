package com.pisey.cleanarchitecture.data.repository

import android.content.Context
import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.data.local.dao.PostDao
import com.pisey.cleanarchitecture.data.local.entity.PostEntity
import com.pisey.cleanarchitecture.data.model.PostResponse
import com.pisey.cleanarchitecture.data.remote.ApiService
import com.pisey.cleanarchitecture.domain.model.Post
import com.pisey.cleanarchitecture.domain.repository.PostRepository
import com.pisey.cleanarchitecture.utils.ConnectUtils

class PostRepositoryImpl(
    private val apiService: ApiService,
    private val postDao: PostDao,
    private val context: Context
) : PostRepository {

    override suspend fun getPosts(): CustomResult<PostResponse> {
        return if (ConnectUtils.isConnected(context)) {
            val postsFromApi = apiService.getPosts()
            val entities = postsFromApi.posts.map { PostEntity(it.id, it.title, it.body) }

            postDao.clearPosts()
            postDao.insertPosts(entities)

            CustomResult.Success(postsFromApi)
        } else {
            val list = postDao.getPosts()?.map { Post(it.id, it.title, it.body) } ?: emptyList()
            CustomResult.Success(PostResponse(posts = list))
        }
    }

}
