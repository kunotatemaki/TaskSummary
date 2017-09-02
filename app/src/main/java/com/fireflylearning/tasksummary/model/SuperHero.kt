package com.fireflylearning.tasksummary.model

import android.os.Parcel
import android.os.Parcelable
import com.fireflylearning.tasksummary.network.model.SuperHeroElementRespose

/**
 * Created by Roll on 31/8/17.
 */

class SuperHero constructor(val name: String?,
                            val photo: String?,
                            val realName: String?,
                            val height: String?,
                            val power: String?,
                            val abilities: String?,
                            val groups: String?) : Parcelable {
    constructor(superhero: SuperHeroElementRespose) : this(
            name = superhero.name,
            photo = superhero.photo,
            realName = superhero.realName,
            height = superhero.height,
            power = superhero.power,
            abilities = superhero.abilities,
            groups = superhero.groups
    )

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
        val CREATOR: Parcelable.Creator<SuperHero> = object : Parcelable.Creator<SuperHero> {
            override fun createFromParcel(source: Parcel): SuperHero = SuperHero(source)
            override fun newArray(size: Int): Array<SuperHero?> = arrayOfNulls(size)
        }
    }
}