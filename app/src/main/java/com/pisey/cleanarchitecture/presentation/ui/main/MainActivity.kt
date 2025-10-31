package com.pisey.cleanarchitecture.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pisey.cleanarchitecture.databinding.ActivityMainBinding
import com.pisey.cleanarchitecture.presentation.ui.post.PostActivity
import com.pisey.cleanarchitecture.presentation.ui.user.UserActivity
import com.pisey.cleanarchitecture.utils.awaitActivityResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val postActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val postResult = data?.getStringExtra("post_result")
                Toast.makeText(this, "Post Result: $postResult", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPost.setOnClickListener {
            lifecycleScope.launch {
                val resultIntent = awaitActivityResult(PostActivity.createIntent(this@MainActivity))
                setResult(resultIntent.resultCode, resultIntent.data)
                if(resultIntent.resultCode == RESULT_OK){
                    val result = resultIntent.data?.getStringExtra("post_result")
                    Toast.makeText(this@MainActivity, "Result: $result", Toast.LENGTH_SHORT).show()
                }

            }

        }
        binding.btnUser.setOnClickListener {
            UserActivity.start(this)
        }
    }
}