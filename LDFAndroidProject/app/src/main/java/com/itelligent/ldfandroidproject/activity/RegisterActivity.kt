package com.itelligent.ldfandroidproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.itelligent.ldfandroidproject.R
import com.itelligent.ldfandroidproject.databinding.ActivityRegisterBinding
import com.itelligent.ldfandroidproject.utils.PreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    @Inject
    lateinit var preferenceUtil: PreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.registerBtn.setOnClickListener{
            if (binding.emailEd.text.isNullOrEmpty()){
                binding.emailEd.error = "Please enter your email"
                return@setOnClickListener
            }else if (binding.passwordEd.text.isNullOrEmpty()){
                binding.passwordEd.error = "Please enter your password"
                return@setOnClickListener
            }else{
                preferenceUtil.saveString(binding.emailEd.text.toString(),binding.passwordEd.text.toString())
                preferenceUtil.saveBoolean(PreferenceUtil.IS_USER_LOGGED_IN,true)
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}