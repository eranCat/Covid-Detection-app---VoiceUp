package com.vocaai.covid19.activities

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.kaopiz.kprogresshud.KProgressHUD
import com.vocaai.covid19.R
import com.vocaai.covid19.models.*
import com.vocaai.covid19.utils.APIManager
import com.vocaai.covid19.utils.TempKind
import com.vocaai.covid19.utils.Units
import com.vocaai.covid19.utils.extensions.toast
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {

    var heightUnits: Units = Units.CENTIMETERS
    var tempKind: TempKind = TempKind.CELSIUS


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        height_group.setOnCheckedChangeListener { _, id ->
            updateHeightET(id)
        }

        submit_btn.setOnClickListener { submit() }
    }

    private fun submit() {
        //to record activity
        val hud = KProgressHUD.create(this)
        hud.show()
        APIManager.sendReport(buildData()) { result ->

            when {
                result.isSuccess -> {
                    toast("Success")
                    val submission = result.getOrNull() as? Submission
                        ?: return@sendReport
                    GlobalData.currentSubmissionId = submission.submissionId
                }
                result.isFailure -> toast("Data submission failed")
            }

            RecordActivity.start(this, RecordInstructions(), 1)

            hud.dismiss()
        }
    }

    private fun updateHeightET(id: Int) {
        when (id) {
            R.id.opt_feet -> {
                heightUnits = Units.FEET
                feet_group.visibility = View.VISIBLE
                centimeters_et.visibility = View.GONE
            }
            R.id.opt_centimeters -> {
                heightUnits = Units.CENTIMETERS
                feet_group.visibility = View.GONE
                centimeters_et.visibility = View.VISIBLE
            }
        }
    }

    private fun buildData(): UserData {

        //foreach edit text -> add info to formData
        val age = age_et.text.toString().toIntOrNull()
        val temp = temp_et.text.toString().toDoubleOrNull() ?: -1.0
        val height = when (heightUnits) {
            Units.CENTIMETERS -> {
                val height = centimeters_et.text.toString()
                    .toIntOrNull() ?: 0

                HeightInfo(heightUnits, height)
            }

            Units.FEET -> {
                val feet = feet_et.text.toString().toIntOrNull()
                    ?: 0

                val inches = inches_et.text.toString().toIntOrNull()
                    ?: 0

                //feet to inches 1 foot = 12 inch
                HeightInfo(heightUnits, feet * 12 + inches)
            }
        }

        //foreach options button -> get selected option
        val country = country_btn.selectedOption
        val smokingHabits = smoke_btn.selectedOption
        val currentStatus = current_status_btn.selectedOption

        val gender = gender_btn.selectedOption

        val isDiagnosed = is_diagnosed.isChecked

        //foreach checkbox -> get checked
        val symptoms = mapCheckBoxes(symptoms)


        val covid19 = Covid19(Temp(tempKind, temp), isDiagnosed, symptoms)

        val formData = FormData(
            height,
            age, gender,
            country, "a", "b", "1234",
            mapCheckBoxes(diseases),
            smokingHabits, currentStatus,
            covid19
        )

        return UserData(formData)
    }

    private fun mapCheckBoxes(group: LinearLayout): MutableList<String> {
        val list = mutableListOf<String>()
        group.children.forEach { view ->
            val checkBox = view as? CheckBox
                ?: return@forEach

            if (checkBox.isChecked) {
                //TODO probably should be only english
                list.add(checkBox.text.toString())
            }
        }
        return list
    }


}