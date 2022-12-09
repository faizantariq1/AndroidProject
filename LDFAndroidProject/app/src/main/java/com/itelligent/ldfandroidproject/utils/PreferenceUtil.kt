package com.itelligent.ldfandroidproject.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PreferenceUtil(context: Context) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveBoolean(key: String, value: Boolean) {
        preference.edit().putBoolean(
            key,
            value
        ).apply()
    }

    fun getBoolean(key: String): Boolean {
        return preference.getBoolean(key, false)
    }

    fun saveString(key: String, value: String) {
        preference.edit().putString(
            key,
            value
        ).apply()
    }

    fun getString(key: String): String {
        return preference.getString(key, "")!!
    }

    companion object {
        const val IS_USER_LOGGED_IN = "is_user_logged_in"
    }
}