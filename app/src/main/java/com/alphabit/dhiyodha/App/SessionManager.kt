package com.alphabit.dhiyodha.App

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.alphabit.dhiyodha.Dashboard.DashboardActivity

class SessionManager(private val _context: Context) {

    private val pref: SharedPreferences
    private val introPref: SharedPreferences
    private val editor: Editor
    private val introEditor: Editor
    private val PRIVATE_MODE = 0
    private lateinit var mSessionManager: SessionManager

    fun setData(key: String?, value: String?) {
        editor.putString(key, value)
        editor.commit()
    }

    fun setData(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun setData(key: String, value: Int) {
        editor.putInt(key, value)
        editor.commit()
    }

    fun setUserLogin(isUserLogin: Boolean) {
        editor.putBoolean(IS_USER_LOGIN, isUserLogin)
        editor.commit()
    }

    fun checkLogin() {
        val i = Intent(_context, DashboardActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        _context.startActivity(i)
        /*if (!isLoggedIn) {
            val i = Intent(_context, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            _context.startActivity(i)
        } else {
            val i = Intent(_context, DashboardActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            _context.startActivity(i)
        }*/
    }

    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_USER_LOGIN, false)

    fun getData(key: String): String? {
        return pref.getString(key, " ")
    }

    fun getData(key: String?, defaultValue: String?): String? {
        return pref.getString(key, defaultValue)
    }

    fun getAuthToken(key: String?): String? {
        return pref.getString(key, "")
    }

    fun getIntData(key: String?): Int {
        return pref.getInt(key, 0)
    }

    /*fun getLanguage(key: String?, defaultValue: String): String? {
        return pref.getString(key, defaultValue)
    }*/

    fun getBooleanData(key: String?): Boolean {
        return pref.getBoolean(key, true)
    }

    fun getBooleanData(key: String?, defaultValue: Boolean): Boolean {
        return pref.getBoolean(key, defaultValue)
    }

    companion object {
        private const val PREFER_NAME = "Dhiyodha"
        private const val INTRO_PREFER_NAME = "Dhiyodha_intro"
        private const val IS_USER_LOGIN = "is_user_loggedIn"
        const val USER_ID = "u_id"
        const val AUTH_TOKEN = "auth_token"
        const val FULL_NAME = "full_name"
        const val USER_NAME = "user_name"
    }

    init {
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE)
        introPref = _context.getSharedPreferences(INTRO_PREFER_NAME, PRIVATE_MODE)
        editor = pref.edit()
        introEditor = introPref.edit()
    }
}


