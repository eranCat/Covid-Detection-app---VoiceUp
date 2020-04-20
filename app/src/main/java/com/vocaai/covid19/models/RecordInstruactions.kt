package com.vocaai.covid19.models

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.StringRes
import com.vocaai.covid19.R
import com.vocaai.covid19.localization.StringEnum
import com.vocaai.covid19.models.RecordInstructions.Stage.*


data class RecordInstructions(var stage: Stage) : Parcelable {
    var nextStage: Stage? = null

    constructor():this(cough){
        nextStage = cough
    }

    fun updateInstruction(context: Context) {
        when (stage) {
            cough -> {
                instruction = RecordInstruction(context
                    , Titles.COUGH, Descriptions.COUGH, "cough"
                )
                nextStage = count
            }
            ABC -> {
                instruction = RecordInstruction(context
                    , Titles.COUNT, Descriptions.COUNT, "count"
                )
                nextStage = ABC
            }
            count -> {
                instruction = RecordInstruction(context
                    , Titles.ABC, Descriptions.ABC, "ABC"
                )
                nextStage = ooo
            }
            aaa -> {
                instruction = RecordInstruction(context
                    , Titles.OOO, Descriptions.OOO, "ooo"
                )
                nextStage = eee
            }
            eee -> {
                instruction = RecordInstruction(context
                    , Titles.EEE, Descriptions.EEE, "eee"
                )
                nextStage = aaa
            }
            ooo -> {
                instruction = RecordInstruction(context
                    , Titles.AAA, Descriptions.AAA, "aaa"
                )
                nextStage = null
            }
        }
    }
    enum class Stage {
        cough,
        ABC,
        count,
        aaa,
        eee,
        ooo,

    }

    var instruction: RecordInstruction? = null
        set(value) {
            field = value
            value ?: return
            when (stage) {

                cough ->
                    nextStage = count

                count ->
                    nextStage = ABC

                ABC ->
                    nextStage = ooo

                ooo ->
                    nextStage = eee

                eee ->
                    nextStage = aaa
                aaa ->
                    nextStage = null

            }
        }

    enum class Titles(@get:StringRes override val res: Int) : StringEnum {
        COUGH(R.string.cough3),
        COUNT(R.string.count1_20),
        ABC(R.string.alphabet),
        EEE(R.string.sayEee),
        OOO(R.string.sayOoo),
        AAA(R.string.sayAaa),
    }

    enum class Descriptions(@get:StringRes override val res: Int) : StringEnum {
        COUGH(R.string.empty),
        COUNT(R.string.empty),
        ABC(R.string.abc),
        EEE(R.string.eeeDesc),
        OOO(R.string.oooDesc),
        AAA(R.string.aaaDesc),
    }

    constructor(source: Parcel) : this(
        Stage.values()[source.readInt()]
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(stage.ordinal)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RecordInstructions> =
            object : Parcelable.Creator<RecordInstructions> {
                override fun createFromParcel(source: Parcel): RecordInstructions =
                    RecordInstructions(source)

                override fun newArray(size: Int): Array<RecordInstructions?> = arrayOfNulls(size)
            }
    }
}