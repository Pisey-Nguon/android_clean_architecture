package com.pisey.cleanarchitecture.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

suspend fun AppCompatActivity.awaitActivityResult(intent: Intent): ActivityResult {
    val key = javaClass.simpleName
    return suspendCancellableCoroutine { continuation ->
        val contract = object : ActivityResultContract<Intent, ActivityResult>() {
            override fun createIntent(context: Context, input: Intent): Intent = input

            override fun parseResult(resultCode: Int, intent: Intent?): ActivityResult {
                return ActivityResult(resultCode, intent)
            }
        }

        val launcher = activityResultRegistry.register(key, contract) { result ->
            continuation.resume(result)
        }

        continuation.invokeOnCancellation {
            launcher.unregister()
        }

        launcher.launch(intent)
    }
}