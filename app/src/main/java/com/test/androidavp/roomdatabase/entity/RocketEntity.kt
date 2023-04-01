package com.test.androidavp.roomdatabase.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class RocketEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "id") val id: String? = "",
    @ColumnInfo(name = "name") val name: String? = "",
    @ColumnInfo(name = "image") val image: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "engineCount") val engineCount: Int = 0,
    @ColumnInfo(name = "activeStatus") val activeStatus: Boolean = false,
    @ColumnInfo(name = "costPerLaunch") val costPerLaunch: Int = 0,
    @ColumnInfo(name = "successRatePer") val successRatePer: Int = 0,
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "wikipedia") val wikipedia: String = "",
    @ColumnInfo(name = "feetInches") val feetInches: String = "",
    @ColumnInfo(name = "height") val height: Double = 0.0,
    @ColumnInfo(name = "diameter") val diameter: Double = 0.0,
    @ColumnInfo(name = "flickrImages") val flickrImages: ArrayList<String>,
) : Parcelable