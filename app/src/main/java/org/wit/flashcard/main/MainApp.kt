package org.wit.flashcard.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.flashcard.models.FlashcardJSONStore
import org.wit.flashcard.models.FlashcardMemStore
import org.wit.flashcard.models.FlashcardStore

class MainApp : Application(), AnkoLogger {

    //val flashcards = ArrayList<FlashcardModel>()
    lateinit var flashcards : FlashcardStore

    override fun onCreate() {
        super.onCreate()
        // flashcards = FlashcardMemStore()
        flashcards = FlashcardJSONStore(applicationContext)
        info("Language Learning started")
    }
}