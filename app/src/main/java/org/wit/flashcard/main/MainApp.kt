package org.wit.flashcard.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.flashcard.models.FlashcardMemStore

class MainApp : Application(), AnkoLogger {

    //val flashcards = ArrayList<FlashcardModel>()
    val flashcards = FlashcardMemStore()

    override fun onCreate() {
        super.onCreate()
        info("LayLa started")
    }
}