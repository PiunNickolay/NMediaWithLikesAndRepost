package ru.netology.nmediawithwall

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmediawithwall.databinding.ActivityMainBinding
import ru.netology.nmediawithwall.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 1_099_999,
            shares = 999,
            views = 10_000
        )
        with(binding) {
            content.text = post.content
            published.text = post.published
            author.text = post.author
            likes.text = countFormat(post.likes)
            shares.text = countFormat(post.shares)
            views.text = countFormat(post.views)


            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) {
                    post.likes++
                    like.setImageResource(R.drawable.ic_liked_24)
                } else {
                    post.likes--
                    like.setImageResource(R.drawable.ic_like)
                }
                likes.text = countFormat(post.likes)
            }
            share.setOnClickListener {
                post.shares++
                shares.text = countFormat(post.shares)
            }
        }


    }

    fun countFormat(count: Int): String {
        if (count >= 1_000_000) {
            val millions = count / 1_000_000
            val remainder = (count % 1_000_000) / 100_000
            return if (remainder > 0) {
                "$millions.${remainder}M"
            } else {
                "$millions M"
            }
        } else if (count >= 10_000) {
            val thousands = count / 1_000
            return "$thousands K"
        } else if (count >= 1_100) {
            val thousands = count / 1_000
            val remainder = (count % 1_000) / 100
            return if (remainder > 0) {
                "$thousands.${remainder}K"
            } else {
                "$thousands K"
            }
        } else if (count >= 1_000) {
            return "${count / 1_000}K"
        } else {
            return count.toString()
        }
    }
}