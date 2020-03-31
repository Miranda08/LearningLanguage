package org.wit.layla.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.layla.models.FlashcardMemStore
import org.wit.layla.models.FlashcardModel

class MainApp : Application(), AnkoLogger {

    //val flashcards = ArrayList<FlashcardModel>()
    val flashcards = FlashcardMemStore()

    override fun onCreate() {
        super.onCreate()
        info("LayLa started")
    }
}