package org.wit.flashcard.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.flashcard.helpers.*
import java.util.*
import kotlin.collections.ArrayList

val JSON_FILE = "flashcards.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<FlashcardModel>>(){}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class FlashcardJSONStore : FlashcardStore, AnkoLogger {

    val context: Context
    var flashcards = mutableListOf<FlashcardModel>()

    constructor(context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    //override from FlashcardStore
    override fun findAll(): List<FlashcardModel> {
        return flashcards
    }

    //override from FlashcardStore
    override fun create(flashcard: FlashcardModel) {
        flashcard.id = generateRandomId()
        flashcards.add(flashcard)
        serialize()
    }

    //override from FlashcardStore
    override fun update(flashcard: FlashcardModel) {
        val flashcardList = findAll() as ArrayList<FlashcardModel>
        var foundFlashcard: FlashcardModel? = flashcardList.find { f -> f.id == flashcard.id }
        if(foundFlashcard != null){
            foundFlashcard.title = flashcard.title
            foundFlashcard.translation = flashcard.translation
            foundFlashcard.description = flashcard.description
            foundFlashcard.image = flashcard.image
        }
        serialize()
    }

    override fun delete(flashcard: FlashcardModel) {
        flashcards.remove(flashcard)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(flashcards, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        flashcards = Gson().fromJson(jsonString, listType)
    }

}