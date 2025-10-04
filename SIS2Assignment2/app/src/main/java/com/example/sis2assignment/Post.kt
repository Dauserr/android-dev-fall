package com.example.sis2assignment

data class Post(
    val text: String,
    val imageUrl: String,
    var liked: Boolean = false
)
