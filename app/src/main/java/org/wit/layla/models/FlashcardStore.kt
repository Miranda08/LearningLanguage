package org.wit.layla.models

interface FlashcardStore {
    fun findAll(): List<FlashcardModel>
    fun create(flashcard: FlashcardModel)
    fun update(flashcard: FlashcardModel)
}