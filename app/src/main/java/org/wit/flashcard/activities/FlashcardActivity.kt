package org.wit.flashcard.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_flashcard.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.flashcard.R
import org.wit.flashcard.helpers.readImage
import org.wit.flashcard.helpers.readImageFromPath
import org.wit.flashcard.helpers.showImagePicker
import org.wit.flashcard.main.MainApp
import org.wit.flashcard.models.FlashcardModel

class FlashcardActivity : AppCompatActivity(), AnkoLogger {

    var flashcard = FlashcardModel()
    lateinit var app : MainApp

    var edit = false

    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        app = application as MainApp

        info("LayLa Main Activity started...")

        //edit flashcard
        if(intent.hasExtra(("flashcard_edit"))) {
            edit = true
            flashcard = intent.extras.getParcelable<FlashcardModel>("flashcard_edit")
            flashcardTitle.setText(flashcard.title)
            flashcardDutch.setText(flashcard.translation)
            description.setText(flashcard.description)
            flashcardImage.setImageBitmap(readImageFromPath(this, flashcard.image))
            if(flashcard.image != null ){
                chooseImage.setText(R.string.change_flashcard_image)
            }
            btnAdd.setText(R.string.save_flashcard)
        }

        //add flashcards
        btnAdd.setOnClickListener() {
            flashcard.title = flashcardTitle.text.toString()
            flashcard.translation = flashcardDutch.text.toString()
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
                info("add Button Pressed: $flashcardTitle & $flashcardDutch")
                setResult((AppCompatActivity.RESULT_OK))
                finish()
            }
        }

        //choose an image from your device
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

    } //end oncreate fun

    //
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

    // recover image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    flashcard.image = data.getData().toString()
                    flashcardImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_flashcard_image)
                }
            }
        }
    } // end onActivityResult

} // end class
