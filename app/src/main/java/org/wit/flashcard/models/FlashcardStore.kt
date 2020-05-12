package org.wit.flashcard.models

interface FlashcardStore {
    fun findAll(): List<FlashcardModel>
    fun create(flashcard: FlashcardModel)
    fun update(flashcard: FlashcardModel)
    fun delete(flashcard: FlashcardModel)
}