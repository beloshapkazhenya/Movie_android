package com.example.movie.model.local

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject

open class MovieDetailsLocal(
    var actors: String? = "",
    var country: String? = "",
    var director: String? = "",
    var genre: String? = "",
    var plot: String? = "",
    var poster: String? = "",
    var released: String? = "",
    var runtime: String? = "",
    var title: String? = "",
    var writer: String? = "",
    var id: String? = ""
) : RealmObject(), Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(actors)
        parcel.writeString(country)
        parcel.writeString(director)
        parcel.writeString(genre)
        parcel.writeString(plot)
        parcel.writeString(poster)
        parcel.writeString(released)
        parcel.writeString(runtime)
        parcel.writeString(title)
        parcel.writeString(writer)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetailsLocal> {
        override fun createFromParcel(parcel: Parcel): MovieDetailsLocal {
            return MovieDetailsLocal(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetailsLocal?> {
            return arrayOfNulls(size)
        }
    }
}

