package com.pisey.cleanarchitecture.presentation.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.domain.model.Post
import com.pisey.cleanarchitecture.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            when(val result = getPostsUseCase.invoke()) {
                is CustomResult.Success<List<Post>> -> {
                    _posts.value = result.data
                }
                is CustomResult.Error -> {
                    // Handle error case, e.g., show a message to the user
                }
            }
        }
    }
}
