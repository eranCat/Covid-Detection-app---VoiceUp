package com.vocaai.covid19.models

import com.vocaai.covid19.utils.TempKind

class Covid19(
    var currentFever: Temp,
    var diagnosedCovid19: Boolean,
    var currentSymptoms: List<String>
) {
    constructor() : this(Temp(), false, emptyList())
}

class Temp(val unit:TempKind,val value:Double){
    constructor():this(TempKind.CELSIUS,36.0)
}