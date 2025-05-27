package com.pisey.cleanarchitecture.data.repository

import android.content.Context
import android.net.ConnectivityManager
import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.data.local.dao.PostDao
import com.pisey.cleanarchitecture.data.local.entity.PostEntity
import com.pisey.cleanarchitecture.data.remote.ApiService
import com.pisey.cleanarchitecture.domain.model.Post
import com.pisey.cleanarchitecture.domain.repository.PostRepository
import com.pisey.cleanarchitecture.utils.ConnectUtils

class PostRepositoryImpl(
    private val apiService: ApiService,
    private val postDao: PostDao,
    private val context: Context
) : PostRepository {

    override suspend fun getPosts(): CustomResult<List<Post>> {
        return if (ConnectUtils.isConnected(context)) {
            val postsFromApi = apiService.getPosts().posts
            val entities = postsFromApi.map { PostEntity(it.id, it.title, it.body) }

            postDao.clearPosts()
            postDao.insertPosts(entities)

            CustomResult.Success(postsFromApi.map { it.toPost() })
        } else {
            CustomResult.Success(postDao.getPosts()?.map { Post(it.id, it.title, it.body) } ?: emptyList<Post>())
        }
    }

}
