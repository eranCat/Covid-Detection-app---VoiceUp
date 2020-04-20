package com.vocaai.covid19.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.vocaai.covid19.R
import com.vocaai.covid19.localization.*
import java.util.*

abstract class SelectOptionButton<T> : AppCompatButton
        where T : Enum<T>, T : StringEnum {

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    var selectedOption: String? = null

    @get:StringRes
    abstract val titleRes: Int

    private val title: String
        get() = with(resources) {
            getString(R.string.select_option, getString(titleRes))
        }

    init {
        this.setBackgroundResource(R.drawable.select_options_btn)
        this.setTextColor(Color.WHITE)
        this.textSize = 16f

        setText(R.string.select_one_option)
//        TODO("Can't use generics")
        //setOnClickListener { showOptions<T>() }
    }

    private inline fun <reified E> showOptions()
            where E : Enum<E>, E : StringEnum {

        val values = enumValues<E>()
        val options = values.map {
            resources.getString(it.res)
        }.toTypedArray()

        AlertDialog.Builder(context)
            .setTitle(title)
            .setItems(options) { dialog, i ->
                dialog.dismiss()
                selectedOption = values[i].serverValue(context)//get default english
                text = options[i]
            }.show()
    }

}

class GenderButton : SelectOptionButton<Gender> {

    override val titleRes = R.string.gender

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
}

class SmokeStatusButton : SelectOptionButton<SmokeStatus> {

    override val titleRes = R.string.smoke

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
}
class CurrentStatusButton : SelectOptionButton<CurrentStatus> {

    override val titleRes = R.string.current_status

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
}

class CountriesButton :AppCompatButton {

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
        if (Locale.getDefault() != Locale.ENGLISH){
            options.sort()
        }

        AlertDialog.Builder(context)
            .setTitle(R.string.select_one_option)
            .setItems(options) { dialog, i ->
                dialog.dismiss()
                selectedOption = options[i]//TODO always get english value
                text = options[i]
            }.show()
    }


}

