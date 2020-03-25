package org.wit.layla.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.activity_flashcard_list.*
import kotlinx.android.synthetic.main.card_flashcard.view.*
import org.jetbrains.anko.startActivityForResult
import org.wit.layla.R
import org.wit.layla.main.MainApp
import org.wit.layla.models.FlashcardModel

class FlashcardListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FlashcardAdapter(app.flashcards)

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

}

class FlashcardAdapter constructor(private var flashcards: List<FlashcardModel>) : RecyclerView.Adapter<FlashcardAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_flashcard, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val flashcard = flashcards[holder.adapterPosition]
        holder.bind(flashcard)
    }

    override fun getItemCount(): Int = flashcards.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(flashcard: FlashcardModel) {
            itemView.flashcardTitle.text = flashcard.title
            itemView.description.text = flashcard.description
        }
    }
}