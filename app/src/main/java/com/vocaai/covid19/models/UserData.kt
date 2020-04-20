package com.vocaai.covid19.models

import android.provider.Settings

class UserData(val formData: FormData) {
    val deviceId = Settings.Secure.ANDROID_ID
    val formRevision = 2
}