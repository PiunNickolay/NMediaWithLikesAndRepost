package ru.netology.nmediawithwall.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmediawithwall.repository.PostRepository
import ru.netology.wallwithlikes.dto.Post

class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    val post: LiveData<Post> = repository.post

    fun like() = repository.like()
    fun share() = repository.share()
}