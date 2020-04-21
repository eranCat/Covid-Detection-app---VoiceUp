package com.vocaai.covid19.views.enum_btns

import android.content.Context
import android.util.AttributeSet
import com.vocaai.covid19.R
import com.vocaai.covid19.views.SelectOptionButton

class GenderButton : SelectOptionButton {

    override val titleRes = R.string.gender
    override val optionsRes = R.array.gender

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

}