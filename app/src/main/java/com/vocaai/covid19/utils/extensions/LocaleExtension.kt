package com.vocaai.covid19.utils.extensions

import java.util.*

object Loc {

    val EnglishLocale = Locale("en")
    val RussianLocale = Locale("ru")


    fun locale(rawName: String?): Locale {
        rawName ?: return Locale.getDefault()
        return _languageNameToLocaleMap[rawName] ?: Locale.getDefault()
    }
}

private val _languageNameToLocaleMap = mapOf(
    "English" to Loc.EnglishLocale,
    "Russian" to Loc.RussianLocale
)

val Locale.languageName: String
    get() {
        for ((lang, locale) in _languageNameToLocaleMap.entries) {
            if (locale.isO3Language == this.isO3Language)
                return lang
        }

        return "English"
    }


val Locale.longId
    get() = when (language) {
        Loc.EnglishLocale.language -> "eng"
        Loc.RussianLocale.language -> "rus"
        else -> "rus"
    }
