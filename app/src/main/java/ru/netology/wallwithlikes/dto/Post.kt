package ru.netology.wallwithlikes.dto

data class Post(
    val id:Long,
    val author:String,
    val content:String,
    val published:String,
    var likedByMe: Boolean = true,
    var likes: Int = 99,
    var shares: Int = 10,
    val views: Int = 10000
)
