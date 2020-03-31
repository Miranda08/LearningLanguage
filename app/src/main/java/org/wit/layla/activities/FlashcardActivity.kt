package org.wit.layla.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_flashcard.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.layla.R
import org.wit.layla.helpers.showImagePicker
import org.wit.layla.main.MainApp
import org.wit.layla.models.FlashcardModel

class FlashcardActivity : AppCompatActivity(), AnkoLogger {

    var flashcard = FlashcardModel()
//    val flashcards = ArrayList<FlashcardModel>()
    lateinit var app : MainApp

    var edit = false

    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        app = application as MainApp

        info("LayLa Main Activity started...")

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if(intent.hasExtra(("flashcard_edit"))) {
            edit = true
            flashcard = intent.extras.getParcelable<FlashcardModel>("flashcard_edit")
            flashcardTitle.setText(flashcard.title)
            description.setText(flashcard.description)
            btnAdd.setText(R.string.save_flashcard)
        }

        btnAdd.setOnClickListener() {
            flashcard.title = flashcardTitle.text.toString()
            flashcard.description = description.text.toString()

            if(flashcard.title.isEmpty()) {
                toast(R.string.enter_flashcard_title)


            }
            else {
                if (edit){
                    app.flashcards.update(flashcard.copy())
                } else {
                    app.flashcards.create(flashcard.copy())
                }
                info("add Button Pressed: $flashcardTitle")
                setResult((AppCompatActivity.RESULT_OK))
                finish()
            }
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_flashcard, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
