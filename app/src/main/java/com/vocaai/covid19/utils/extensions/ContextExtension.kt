package com.vocaai.covid19.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(message:String)=
    Toast.makeText(this,message,Toast.LENGTH_SHORT)

fun Context.toast(@StringRes message: Int)=
    Toast.makeText(this,message,Toast.LENGTH_SHORT)