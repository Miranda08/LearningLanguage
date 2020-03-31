package org.wit.layla.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlashcardModel (var id: Long = 0,
                           var title: String = "",
                           var description: String = "") : Parcelable