package com.itelligent.ldfandroidproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.itelligent.ldfandroidproject.R
import com.itelligent.ldfandroidproject.utils.PreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceUtil: PreferenceUtil
    private var mDelayHandler: Handler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler(Looper.getMainLooper())
        mDelayHandler?.postDelayed(mRunnable, 3000)
    }

    private val mRunnable: Runnable = Runnable {
            if (preferenceUtil.getBoolean(PreferenceUtil.IS_USER_LOGGED_IN)) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
    }

    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler?.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}