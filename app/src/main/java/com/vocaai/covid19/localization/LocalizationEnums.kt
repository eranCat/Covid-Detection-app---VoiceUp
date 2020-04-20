package com.vocaai.covid19.localization

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import com.vocaai.covid19.R
import java.util.*

interface StringEnum {
    @get:StringRes
    val res: Int

    fun serverValue(context: Context): String {
        val root = Locale.ROOT

        val conf = Configuration(context.resources.configuration)
            .apply {
                setLocale(root)
            }

        val localized = context.createConfigurationContext(conf)

        return localized.resources.getString(res)
    }
}

enum class Gender(override val res: Int) : StringEnum {
    MALE(R.string.male),
    FEMALE(R.string.female),
    OTHER(R.string.other);
}

enum class SmokeStatus(override val res: Int) : StringEnum {
    CURRENTLY_SMOKE(R.string.currently_smoke),
    USED_TO_SMOKE(R.string.used_to_smoke),
    NEVER_SMOKED(R.string.never_smokeed);
}


enum class Diagnoses(override val res: Int) : StringEnum {
    DIABETES(R.string.Diabetes),
    HYPERTENSION(R.string.Hypertension),
    ISCHEMIC_HEART(R.string.Ischemic_heart),
    ASTHMA(R.string.Asthma),
    ALLERGIES(R.string.Allergies),
    CHRONIC_LUNG(R.string.Chronic_lung),
    CHRONIC_KIDNEY(R.string.Chronic_kidney),
    SEASONAL_ALLERGIES(R.string.Seasonal_allergies);
}


enum class CurrentStatus(override val res: Int) : StringEnum {
    notIn(R.string.not_isolated),
    hospitalized(R.string.Hospitalized),
    dueToCovid(R.string.isolated_diagnosed),
    dueToTravel(R.string.isolated_travel),
    contactWithIndividual(R.string.isolated_contant),
    dueToGovernment(R.string.isolated_gov)
}

enum class Symptoms(override val res: Int) : StringEnum {
    feelWell(R.string.feel_well),
    fever(R.string._fever),
    dryCough(R.string.Dry_cough),
    coughWithSputum(R.string.cougn_sputum),
    fatigue(R.string.Fatigue),
    muscleAches(R.string.Muscle_aches),
    headache(R.string.Headache),
    soreThroat(R.string.Sore_throat),
    sneezing(R.string._sneezing),
    shortnessOfBreath(R.string.Shortness_of_breath),
    runnyNose(R.string.Runny_nose_Nasal_congestion),
    itching(R.string.Itching),
    diarrhea(R.string.Diarrhea),
    nausea(R.string.Nausea_vomit);

}


enum class TemperatureType(override val res: Int) : StringEnum {
    celsious(R.string.cels),
    fahrenheit(R.string.Fahrenheit)
}

enum class HeightType(override val res: Int) : StringEnum {
    feet(R.string.feet),
    centimeters(R.string.centitmeters),
}
