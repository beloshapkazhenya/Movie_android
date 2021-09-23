package com.example.movie.model.response

import android.os.Parcel
import android.os.Parcelable

data class Search(
    val Poster: String?,
    val Title: String?,
    val Type: String?,
    val Year: String?,
    val imdbID: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Poster)
        parcel.writeString(Title)
        parcel.writeString(Type)
        parcel.writeString(Year)
        parcel.writeString(imdbID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Search> {
        override fun createFromParcel(parcel: Parcel): Search {
            return Search(parcel)
        }

        override fun newArray(size: Int): Array<Search?> {
            return arrayOfNulls(size)
        }
    }
}
