package com.disneycodechallenge.ui.view.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GuestsModel(var guestName: String, var guestChecked: Boolean = false) : Parcelable
