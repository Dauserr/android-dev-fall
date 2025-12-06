package com.example.sis5assignment

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(private val context: Context) {

    private val postDao = PostDatabase.getInstance(context).postDao()

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            ?: return false
        return networkCapabilities.hasCapability(
            android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
    }

    suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {
        return@withContext try {
            if (isNetworkAvailable()) {
                val posts = RetrofitClient.apiService.getPosts()
                postDao.deleteAllPosts()
                postDao.insertPosts(posts)
                posts
            } else {
                postDao.getAllPosts()
            }
        } catch (e: Exception) {
            postDao.getAllPosts()
        }
    }
}
