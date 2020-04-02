package org.wit.flashcard.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_flashcard.view.*
import org.wit.flashcard.R
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

        fun bind(flashcard: FlashcardModel, listener: FlashcardListener) {
            itemView.flashcardTitle.text = flashcard.title
            itemView.description.text = flashcard.description
            itemView.setOnClickListener{ listener.onFlashcardClick(flashcard) }
        }
    }
}
