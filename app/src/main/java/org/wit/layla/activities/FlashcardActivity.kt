package org.wit.layla.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_flashcard.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.layla.R
import org.wit.layla.main.MainApp
import org.wit.layla.models.FlashcardModel

class FlashcardActivity : AppCompatActivity(), AnkoLogger {

    var flashcard = FlashcardModel()
//    val flashcards = ArrayList<FlashcardModel>()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        app = application as MainApp

        info("LayLa Main Activity started...")

        btnAdd.setOnClickListener() {
            flashcard.title = flashcardTitle.text.toString()
            flashcard.description = description.text.toString()

            if(flashcard.title.isNotEmpty()) {
                app.flashcards.add(flashcard.copy())
                info("add Button Pressed: $flashcard")
                app.flashcards.forEach{info("add Button Pressed: ${it.title}, ${it.description}")}
                setResult((AppCompatActivity.RESULT_OK))
                finish()
            }
            else {
                toast("Please Enter a title")
            }
        }
    }
}
