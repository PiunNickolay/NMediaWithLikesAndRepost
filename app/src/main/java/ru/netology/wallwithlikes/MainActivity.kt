package ru.netology.nmediawithwall

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ru.netology.nmediawithwall.viewmodel.PostViewModel
import ru.netology.wallwithlikes.R
import ru.netology.wallwithlikes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.post.observe(this, Observer { post ->
            with(binding) {
                content.text = post.content
                published.text = post.published
                author.text = post.author
                likes.text = countFormat(post.likes)
                shares.text = countFormat(post.shares)
                views.text = countFormat(post.views)

                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like
                )
            }
        })

        with(binding) {
            like.setOnClickListener {
                viewModel.like()
            }
            share.setOnClickListener {
                viewModel.share()
            }
        }
    }

    private fun countFormat(count: Int): String {
        return if (count >= 1_000_000) {
            val millions = count / 1_000_000
            val remainder = (count % 1_000_000) / 100_000
            if (remainder > 0) "$millions.${remainder}M" else "$millions M"
        } else if (count >= 10_000) {
            val thousands = count / 1_000
            "$thousands K"
        } else if (count >= 1_100) {
            val thousands = count / 1_000
            val remainder = (count % 1_000) / 100
            if (remainder > 0) "$thousands.${remainder}K" else "$thousands K"
        } else if (count >= 1_000) {
            "${count / 1_000}K"
        } else {
            count.toString()
        }
    }
}