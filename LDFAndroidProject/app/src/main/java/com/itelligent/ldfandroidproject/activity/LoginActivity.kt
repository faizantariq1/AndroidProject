package com.itelligent.ldfandroidproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.itelligent.ldfandroidproject.R
import com.itelligent.ldfandroidproject.databinding.ActivityLoginBinding
import com.itelligent.ldfandroidproject.utils.PreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    @Inject
    lateinit var preferenceUtil: PreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginBtn.setOnClickListener {
            if (binding.emailEd.text.isNullOrEmpty()){
                binding.emailEd.error = "Please enter your email"
                return@setOnClickListener
            }else if (binding.passwordEd.text.isNullOrEmpty()){
                binding.passwordEd.error = "Please enter your password"
                return@setOnClickListener
            }else{
                var password = preferenceUtil.getString(binding.emailEd.text.toString())
                if (binding.passwordEd.text.toString().equals(password)){
                    preferenceUtil.saveBoolean(PreferenceUtil.IS_USER_LOGGED_IN,true)
                    binding.errorTxt.visibility = View.GONE
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    binding.errorTxt.visibility = View.VISIBLE
                }
            }
        }

        binding.signUpBtn.setOnClickListener {
                val intent = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intent)
                finish()
        }

    }
}