package com.itelligent.ldfandroidproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.itelligent.ldfandroidproject.R
import com.itelligent.ldfandroidproject.adapter.AllUsersRecycleViewAdapter
import com.itelligent.ldfandroidproject.databinding.ActivityMainBinding
import com.itelligent.ldfandroidproject.utils.PreferenceUtil
import com.itelligent.ldfandroidproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var usersAdapter: AllUsersRecycleViewAdapter
    @Inject
    lateinit var preferenceUtil: PreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpUI()
        setUpObservers()
    }

    private fun setUpUI() {
        usersAdapter = AllUsersRecycleViewAdapter(this )
        usersAdapter.onItemClick = { position ->
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra("user", mainViewModel.userList.value?.get(position))
            startActivity(intent)

        }
        binding.usersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }

        binding.logoutBtn.setOnClickListener {
            preferenceUtil.saveBoolean(PreferenceUtil.IS_USER_LOGGED_IN,false)
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



    private fun setUpObservers() {
        mainViewModel.userList.observe(this) { usersList ->
            usersList?.let {
                usersAdapter.updateList(it)
            }
        }

        mainViewModel.getUsers()
    }
}