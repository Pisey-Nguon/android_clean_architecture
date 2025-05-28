package com.pisey.cleanarchitecture.presentation.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.data.model.UserResponse
import com.pisey.cleanarchitecture.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _mutableUserLiveData = MutableLiveData<CustomResult<UserResponse>>()
    val mutableUserLiveData: LiveData<CustomResult<UserResponse>> = this._mutableUserLiveData

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _mutableUserLiveData.value = getUsersUseCase.invoke()
        }
    }
}