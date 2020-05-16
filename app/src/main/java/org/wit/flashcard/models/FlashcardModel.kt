package org.wit.flashcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlashcardModel (var id: Long = 0,
                           var title: String = "",
                           var translation: String = "",
                           var description: String = "",
                           var image: String = "") : Parcelable