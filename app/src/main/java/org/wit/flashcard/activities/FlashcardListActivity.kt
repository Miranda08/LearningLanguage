package org.wit.flashcard.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_flashcard_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.flashcard.R
import org.wit.flashcard.main.MainApp
import org.wit.flashcard.models.FlashcardModel

class FlashcardListActivity : AppCompatActivity(), FlashcardListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard_list)
        app = application as MainApp

        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager // recyclerView is a widget in activity_flashcard_list.xml
//        recyclerView.adapter = FlashcardAdapter(app.flashcards.findAll(), this) -> new one under this
        loadFlashcards()

        //enable action bar and set title
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<FlashcardActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    //edit a flashcard
    override fun onFlashcardClick(flashcard: FlashcardModel) {
        startActivityForResult(intentFor<FlashcardActivity>().putExtra("flashcard_edit", flashcard), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        recyclerView.adapter?.notifyDataSetChanged() -> new one under this
        loadFlashcards()
        super.onActivityResult(requestCode, resultCode, data)
    }

    //load (new) flashcards
    private fun loadFlashcards() {
        showFlashcards(app.flashcards.findAll())
    }

    //show flashcards
    fun showFlashcards (flashcards: List<FlashcardModel>) {
        recyclerView.adapter = FlashcardAdapter(flashcards, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}

