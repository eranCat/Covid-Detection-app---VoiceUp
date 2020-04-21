package com.vocaai.covid19.models

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.vocaai.covid19.R


data class RecordInstructions(var stage: Stage) : Parcelable {
    var nextStage: Stage? = null

    constructor() : this(Stage.COUGH) {
        nextStage = Stage.COUGH.next
    }

    fun updateInstruction(context: Context) {
        val descriptions = context.resources.getStringArray(DESC_ARR_RES)
        val titles = context.resources.getStringArray(TITLE_ARR_RES)

        val index = stage.ordinal

        val title = titles[index]
        val description = descriptions[index]

        val stageName = stage.name//TODO check if the name is valid here
        instruction = RecordInstruction(title, description, stageName)

        nextStage = stage.next

    }

    var instruction: RecordInstruction? = null
        set(value) {
            field = value
            value ?: return
            nextStage = stage.next
        }

    constructor(source: Parcel) : this(
        Stage.values()[source.readInt()]
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(stage.ordinal)
    }

    companion object {
        private const val DESC_ARR_RES = R.array.descriptions
        private const val TITLE_ARR_RES = R.array.titles

        @JvmField
        val CREATOR: Parcelable.Creator<RecordInstructions> =
            object : Parcelable.Creator<RecordInstructions> {
                override fun createFromParcel(source: Parcel): RecordInstructions =
                    RecordInstructions(source)

                override fun newArray(size: Int): Array<RecordInstructions?> = arrayOfNulls(size)
            }
    }
}