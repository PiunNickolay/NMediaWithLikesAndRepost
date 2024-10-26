package ru.netology.nmediawithwall.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.wallwithlikes.dto.Post

class PostRepository {
    private val _post = MutableLiveData(
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу...",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 1_099_999,
            shares = 999,
            views = 10_000
        )
    )
    val post: LiveData<Post> = _post

    fun like() {
        _post.value?.let {
            val updatedPost = it.copy(
                likedByMe = !it.likedByMe,
                likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
            )
            _post.value = updatedPost
        }
    }

    fun share() {
        _post.value?.let {
            _post.value = it.copy(shares = it.shares + 1)
        }
    }
}