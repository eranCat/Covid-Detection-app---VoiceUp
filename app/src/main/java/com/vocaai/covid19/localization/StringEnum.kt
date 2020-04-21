package com.vocaai.covid19.localization

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import com.vocaai.covid19.R
import java.util.*

interface StringEnum {
    @get:StringRes
    val res: Int

    fun serverValue(context: Context): String {
        val root = Locale.ROOT

        val conf = Configuration(context.resources.configuration)
            .apply {
                setLocale(root)
            }

        val localized = context.createConfigurationContext(conf)

        return localized.resources.getString(res)
    }
}
