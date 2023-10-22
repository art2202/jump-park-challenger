package com.example.jumpparkchallenger.core.utils

import android.content.Context
import android.content.SharedPreferences

open class SharedPreferenceHelper(context: Context) {
    private var sharedPreferencesEditor : SharedPreferences.Editor
    private var sharedPreferences : SharedPreferences
    private val TAG_TOKEN = "JumpParkChallengerToken"

    init {
        sharedPreferencesEditor = context.getSharedPreferences("JumpParkSharedPreferences", Context.MODE_PRIVATE).edit()
        sharedPreferences = context.getSharedPreferences("JumpParkSharedPreferences", Context.MODE_PRIVATE)
    }

    fun saveToken(token : String){
        sharedPreferencesEditor.putString(TAG_TOKEN, token)
        sharedPreferencesEditor.apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString(TAG_TOKEN, "") ?: ""
    }

    fun clearMemory() {
        sharedPreferencesEditor.clear().commit()
    }
}