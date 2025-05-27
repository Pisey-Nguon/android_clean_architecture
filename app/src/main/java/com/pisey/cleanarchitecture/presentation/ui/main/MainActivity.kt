package com.pisey.cleanarchitecture.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pisey.cleanarchitecture.databinding.ActivityMainBinding
import com.pisey.cleanarchitecture.presentation.ui.post.PostActivity
import com.pisey.cleanarchitecture.presentation.ui.user.UserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPost.setOnClickListener {
            PostActivity.start(this)
        }
        binding.btnUser.setOnClickListener {
            UserActivity.start(this)
        }
    }
}