package com.vocaai.covid19.views.enum_btns

import android.content.Context
import android.util.AttributeSet
import com.vocaai.covid19.R
import com.vocaai.covid19.views.SelectOptionButton

class CurrentStatusButton : SelectOptionButton {

    override val titleRes = R.string.current_status
    override val optionsRes = R.array.status_options

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

}