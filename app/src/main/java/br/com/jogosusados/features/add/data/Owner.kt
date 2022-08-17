package br.com.jogosusados.features.add.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    val name: String,
    val phone: String,
    val email: String
) : Parcelable
