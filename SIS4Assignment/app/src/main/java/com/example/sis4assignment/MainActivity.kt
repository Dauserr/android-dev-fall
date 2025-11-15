package com.example.sis4assignment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        errorTextView = findViewById(R.id.errorTextView)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchPosts()
    }

    private fun fetchPosts() {
        showLoading()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val posts = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getPosts()
                }
                showContent(posts)
            } catch (e: Exception) {
                showError(e.message ?: "Unknown error occurred")
            }
        }
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        errorTextView.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

    private fun showContent(posts: List<Post>) {
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        recyclerView.adapter = PostAdapter(posts)
    }

    private fun showError(message: String) {
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        errorTextView.text = "Error: $message"
    }
}