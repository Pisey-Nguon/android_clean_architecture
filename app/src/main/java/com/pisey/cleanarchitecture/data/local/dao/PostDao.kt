package com.pisey.cleanarchitecture.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pisey.cleanarchitecture.data.local.entity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    suspend fun getPosts(): List<PostEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query("DELETE FROM posts")
    suspend fun clearPosts()
}
