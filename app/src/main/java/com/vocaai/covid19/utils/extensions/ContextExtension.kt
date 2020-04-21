package com.vocaai.covid19.utils.extensions

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.annotation.StringRes
import java.util.*

fun Context.toast(message:String)=
    Toast.makeText(this,message,Toast.LENGTH_SHORT)

fun Context.toast(@StringRes message: Int)=
    Toast.makeText(this,message,Toast.LENGTH_SHORT)

val Context.rootLocalized : Context
get() {
    val root = Locale.ROOT
    val conf = Configuration(resources.configuration)
        .apply {
            setLocale(root)
        }

    return createConfigurationContext(conf)
}