package org.wit.flashcard.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_flashcard.view.*
import kotlinx.android.synthetic.main.card_flashcard.view.*
import kotlinx.android.synthetic.main.card_flashcard.view.description
import kotlinx.android.synthetic.main.card_flashcard.view.flashcardTitle
import kotlinx.android.synthetic.main.card_flashcard.view.flashcardDutch
import org.wit.flashcard.R
import org.wit.flashcard.helpers.readImageFromPath
import org.wit.flashcard.models.FlashcardModel


interface FlashcardListener {
    fun onFlashcardClick(flashcard: FlashcardModel)
}

class FlashcardAdapter constructor(private var flashcards: List<FlashcardModel>,
                                   private val listener: FlashcardListener) : RecyclerView.Adapter<FlashcardAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_flashcard, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val flashcard = flashcards[holder.adapterPosition]
        holder.bind(flashcard, listener)
    }

    override fun getItemCount(): Int = flashcards.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // for showing the items
        fun bind(flashcard: FlashcardModel, listener: FlashcardListener) {
            itemView.flashcardTitle.text = flashcard.title
            itemView.flashcardDutch.text = flashcard.translation
            itemView.description.text = flashcard.description
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, flashcard.image))
            itemView.setOnClickListener{ listener.onFlashcardClick(flashcard) }
        }
    } // end class MainHolder

} // end class FlashcardAdapter
