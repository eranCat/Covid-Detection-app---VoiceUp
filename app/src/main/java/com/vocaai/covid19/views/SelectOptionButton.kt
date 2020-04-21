package com.vocaai.covid19.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.vocaai.covid19.R
import com.vocaai.covid19.utils.extensions.rootLocalized

abstract class SelectOptionButton : AppCompatButton {

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    var selectedOption: String? = null

    @get:StringRes
    abstract val titleRes: Int

    @get:ArrayRes
    abstract val optionsRes: Int

    private val options by lazy { resources.getStringArray(optionsRes) }
    private val enOptions by lazy {
        context.rootLocalized.resources.getStringArray(optionsRes)
    }

    private val title: String
        get() = with(resources) {
            getString(R.string.select_option, getString(titleRes))
        }

    init {
        this.setBackgroundResource(R.drawable.select_options_btn)
        this.setTextColor(Color.WHITE)
        this.textSize = 16f

        setText(R.string.select_one_option)

        setOnClickListener { showOptions() }
    }

    private fun showOptions() =
        AlertDialog.Builder(context)
            .setTitle(title)
            .setItems(optionsRes) { dialog, i ->
                dialog.dismiss()
                selectedOption = enOptions[i]//get default english
                text = options[i]
            }.show()


}