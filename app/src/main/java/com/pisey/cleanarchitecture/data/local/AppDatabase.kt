package com.pisey.cleanarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pisey.cleanarchitecture.data.local.dao.PostDao
import com.pisey.cleanarchitecture.data.local.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
