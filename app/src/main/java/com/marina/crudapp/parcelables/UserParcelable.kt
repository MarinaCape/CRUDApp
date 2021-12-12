package com.marina.crudapp.parcelables

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserParcelable(
    val id: String,
    val name: String?,
    val birthdate: String?
): Parcelable