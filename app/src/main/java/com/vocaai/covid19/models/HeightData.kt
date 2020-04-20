package com.vocaai.covid19.models

import com.vocaai.covid19.localization.HeightType

class HeightData {
    var heightType = HeightType.centimeters
    var feet: Int = 0
    var inches = 0
    var centimeters = 0

    fun getData(): Map<String, Any> {
        val unit = heightType.res//TODO might not need
        return when (heightType) {
            HeightType.feet -> {
                val value = (feet + inches).toDouble()
                mapOf("unit" to unit, "value" to value)
            }
            HeightType.centimeters ->
                mapOf("unit" to unit, "value" to centimeters)
        }
    }
}