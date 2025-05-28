package com.pisey.cleanarchitecture.presentation.ui.post

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.databinding.ActivityPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PostActivity::class.java))
        }
    }

    private lateinit var binding: ActivityPostBinding
    private val viewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.mutablePostLiveData.observe(this) { result ->
            when (result) {
                is CustomResult.Error -> binding.tvPosts.text =
                    result.error.message ?: "An error occurred"

                is CustomResult.Success -> {
                    binding.tvPosts.text = result.data.posts.joinToString("\n") { it.title }
                }
            }
        }
    }

}