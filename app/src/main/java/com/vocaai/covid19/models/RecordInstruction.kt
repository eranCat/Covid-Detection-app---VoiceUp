package com.vocaai.covid19.models

import android.content.Context
import android.os.Parcel
import android.os.Parcelable

data class RecordInstruction(
    var title: String,
    var description: String,
    var recordName: String
) : Parcelable {
    constructor(
        context: Context,
        title: RecordInstructions.Titles,
        desc: RecordInstructions.Descriptions,
        recordName: String
    ) : this(
        title.serverValue(context),
        desc.serverValue(context),
        recordName
    )

    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(description)
        writeString(recordName)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RecordInstruction> =
            object : Parcelable.Creator<RecordInstruction> {
                override fun createFromParcel(source: Parcel): RecordInstruction =
                    RecordInstruction(source)

                override fun newArray(size: Int): Array<RecordInstruction?> = arrayOfNulls(size)
            }
    }
}