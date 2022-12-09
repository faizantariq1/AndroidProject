package com.itelligent.ldfandroidproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.itelligent.ldfandroidproject.R
import com.itelligent.ldfandroidproject.data.model.UserResponse
import com.itelligent.ldfandroidproject.databinding.ActivityDetailBinding
import com.itelligent.ldfandroidproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val user = intent.getSerializableExtra("user") as UserResponse
        binding.userNametxt.text = user.login
        Glide.with(this)
            .load(user.avatar_url)
            .fitCenter()
            .into(binding.userIv)
    }
}