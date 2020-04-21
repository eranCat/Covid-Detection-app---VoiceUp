package com.vocaai.covid19.models

enum class Stage {
    COUGH,
    COUNT,
    ABC,
    EEE,
    AAA,
    OOO;

    val next
        get() = values().getOrNull(ordinal + 1)
}