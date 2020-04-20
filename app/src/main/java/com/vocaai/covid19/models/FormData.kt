package com.vocaai.covid19.models

import com.vocaai.covid19.utils.Units

class FormData(
    var height:HeightInfo?,
    var age: Int?,
    var gender: String?,
    var country: String?,
    var address: String?,
    var city: String?,
    var zipcode: String?,
    var backgroundDiseases: List<String>?,// arrayOf(diagnoseMultiSelectionView.getData()),
    var smokingHabits: String?,
    var status: String?,
    var covid19: Covid19?
) {
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}
class HeightInfo(val unit: Units, val value:Int)