package com.fireflylearning.tasksummary.model

import android.os.Parcel
import android.os.Parcelable
import com.fireflylearning.tasksummary.network.model.TaskElementResponse

/**
 * Created by Roll on 31/8/17.
 */

class Task constructor(val name: String?,
                       val photo: String?,
                       val realName: String?,
                       val height: String?,
                       val power: String?,
                       val abilities: String?,
                       val groups: String?) : Parcelable {
    /*constructor(superhero: TaskElementResponse) : this(
            name = superhero.name,
            photo = superhero.photo,
            realName = superhero.realName,
            height = superhero.height,
            power = superhero.power,
            abilities = superhero.abilities,
            groups = superhero.groups
    )*/

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(photo)
        writeString(realName)
        writeString(height)
        writeString(power)
        writeString(abilities)
        writeString(groups)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Task> = object : Parcelable.Creator<Task> {
            override fun createFromParcel(source: Parcel): Task = Task(source)
            override fun newArray(size: Int): Array<Task?> = arrayOfNulls(size)
        }
    }
}