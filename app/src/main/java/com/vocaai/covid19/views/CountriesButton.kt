package com.vocaai.covid19.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.vocaai.covid19.R
import com.vocaai.covid19.utils.extensions.rootLocalized
import java.util.*

class CountriesButton : AppCompatButton {

    var selectedOption:String? = null

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        this.setTextColor(Color.WHITE)
        this.textSize = 16f

        setText(R.string.select_one_option)
        setOnClickListener { showOptions() }
    }

    private fun showOptions() {

        val options = resources.getStringArray(R.array.countries)

        val enOptions = context.rootLocalized
            .resources.getStringArray(R.array.countries)

        if (Locale.getDefault() != Locale.ENGLISH){
            options.sort()
        }

        AlertDialog.Builder(context)
            .setTitle(R.string.select_one_option)
            .setItems(options) { dialog, i ->
                dialog.dismiss()
                selectedOption = enOptions[i]
                text = options[i]
            }.show()
    }


}