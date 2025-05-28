package com.pisey.cleanarchitecture.core

sealed class CustomResult<out T> {
    data class Success<out T>(val data: T) : CustomResult<T>()
    data class Error(val error: Exception) : CustomResult<Nothing>()
}

fun <T> T.toCustomResult(): CustomResult<T> {
    return try {
        CustomResult.Success(this)
    } catch (e: Exception) {
        CustomResult.Error(e)
    }
}