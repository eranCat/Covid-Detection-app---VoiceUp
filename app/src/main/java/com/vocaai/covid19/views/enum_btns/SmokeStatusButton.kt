package com.vocaai.covid19.views.enum_btns

import android.content.Context
import android.util.AttributeSet
import com.vocaai.covid19.R
import com.vocaai.covid19.views.SelectOptionButton

class SmokeStatusButton : SelectOptionButton {

    override val titleRes = R.string.smoke
    override val optionsRes = R.array.smoking

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

}