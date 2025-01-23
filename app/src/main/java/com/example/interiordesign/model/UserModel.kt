package com.example.interiordesign.model

import android.os.Parcel
import android.os.Parcelable

data class UserModel(
    var userId: String = "",
    var fullname: String = "",
    var email: String = "",
    var password: String = "",
    var confirmpassword: String = "",
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(fullname)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(confirmpassword)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}

