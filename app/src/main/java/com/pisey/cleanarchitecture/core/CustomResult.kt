package com.pisey.cleanarchitecture.core

sealed class CustomResult<out T> {
    data class Success<out T>(val data: T) : CustomResult<T>()
    data class Error(val error: Exception) : CustomResult<Nothing>()
}