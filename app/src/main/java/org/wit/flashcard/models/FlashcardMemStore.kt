package org.wit.flashcard.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class FlashcardMemStore : FlashcardStore, AnkoLogger {

    val flashcards = ArrayList<FlashcardModel>()

    override fun findAll(): List<FlashcardModel> {
        return flashcards
    }

    override fun create(flashcard: FlashcardModel) {
        flashcard.id = getId()
        flashcards.add(flashcard)
        logAll()
    }

    override fun update(flashcard: FlashcardModel) {
        var foundFlashcard: FlashcardModel? = flashcards.find { f -> f.id == flashcard.id }
        if (foundFlashcard != null) {
            foundFlashcard.title = flashcard.title
            foundFlashcard.translation = flashcard.translation
            foundFlashcard.description = flashcard.description
            foundFlashcard.image = flashcard.image
            logAll()
        }
    }

    override fun delete(flashcard: FlashcardModel) {
        flashcards.remove(flashcard)
    }

    fun logAll() {
        flashcards.forEach{ info("$it")}
    }

}