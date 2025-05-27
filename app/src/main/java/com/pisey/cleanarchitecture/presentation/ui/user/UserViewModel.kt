package com.pisey.cleanarchitecture.presentation.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.domain.model.User
import com.pisey.cleanarchitecture.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getUsersUseCase.invoke()
            when(result){
                is CustomResult.Success<List<User>> -> {
                    _users.value = result.data
                }
                is CustomResult.Error -> {
                    // Handle error, e.g., show a message to the user
                }
            }
        }
    }
}