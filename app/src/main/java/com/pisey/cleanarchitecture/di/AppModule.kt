package com.pisey.cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.pisey.cleanarchitecture.data.local.AppDatabase
import com.pisey.cleanarchitecture.data.local.dao.PostDao
import com.pisey.cleanarchitecture.data.remote.ApiService
import com.pisey.cleanarchitecture.data.repository.PostRepositoryImpl
import com.pisey.cleanarchitecture.data.repository.UserRepositoryImpl
import com.pisey.cleanarchitecture.domain.repository.PostRepository
import com.pisey.cleanarchitecture.domain.repository.UserRepository
import com.pisey.cleanarchitecture.domain.usecase.GetPostsUseCase
import com.pisey.cleanarchitecture.domain.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository =
        UserRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideGetUsersUseCase(repository: UserRepository): GetUsersUseCase =
        GetUsersUseCase(repository)

    @Provides
    @Singleton
    fun providePostRepository(
        apiService: ApiService,
        postDao: PostDao,
        @ApplicationContext context: Context
    ): PostRepository = PostRepositoryImpl(apiService, postDao, context)

    @Provides
    @Singleton
    fun provideGetPostsUseCase(repository: PostRepository): GetPostsUseCase =
        GetPostsUseCase(repository)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()


}