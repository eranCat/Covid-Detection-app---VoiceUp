package com.vocaai.covid19

import android.app.Application
import android.content.SharedPreferences
import com.vocaai.covid19.utils.SharedPrefsHelper

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        SharedPrefsHelper.initWithContext(this)
    }
}