package com.pisey.cleanarchitecture.presentation.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.data.model.PostResponse
import com.pisey.cleanarchitecture.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    private val _mutablePostLiveData = MutableLiveData<CustomResult<PostResponse>>()
    val mutablePostLiveData: LiveData<CustomResult<PostResponse>> = _mutablePostLiveData

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            _mutablePostLiveData.value = getPostsUseCase.invoke()
        }
    }
}
