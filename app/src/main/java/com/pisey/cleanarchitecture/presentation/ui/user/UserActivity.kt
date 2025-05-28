package com.pisey.cleanarchitecture.presentation.ui.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pisey.cleanarchitecture.core.CustomResult
import com.pisey.cleanarchitecture.databinding.ActivityUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, UserActivity::class.java))
        }
    }

    private lateinit var binding: ActivityUserBinding
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.mutableUserLiveData.observe(this) { result ->
            when(result){
                is CustomResult.Error -> binding.textView.text = result.error.message ?: "An error occurred"
                is CustomResult.Success -> {
                    binding.textView.text = result.data.users.joinToString("\n") { it.firstName }
                }
            }
        }
    }


}