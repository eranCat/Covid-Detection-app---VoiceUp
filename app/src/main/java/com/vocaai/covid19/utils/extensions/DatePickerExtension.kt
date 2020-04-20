package com.vocaai.covid19.utils.extensions

import android.widget.DatePicker
import java.util.*

fun DatePicker.set18YearValidation() =

    with(Calendar.getInstance(TimeZone.getTimeZone("UTC"))) {
        time = Date()

        val minAge = 18

        add(Calendar.YEAR, -minAge)

        maxDate = timeInMillis

        add(Calendar.YEAR, -150 + minAge)

        minDate = timeInMillis

    }