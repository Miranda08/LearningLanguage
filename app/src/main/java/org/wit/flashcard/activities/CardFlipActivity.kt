package org.wit.flashcard.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.wit.flashcard.R


class CardFlipActivity : FragmentActivity() {

    var showingBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_activity_card_flip)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.container, CardFrontFragment())
//                .commit()
//        }

//        btnFlip.setOnClickListener() {
//            flipCard()
//        }
    }

    class CardFrontFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater,
                                  container: ViewGroup?,
                                  savedInstanceState: Bundle?
        ): View = inflater.inflate(R.layout.card_flashcard, container, false)
    }

    class CardBackFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View = inflater.inflate(R.layout.card_back_flashcard, container, false)
    }

    private fun flipCard() {
        if (showingBack) {
            supportFragmentManager.popBackStack()
            showingBack = false
            return
        }

        // Flip to the back.

        showingBack = true

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        supportFragmentManager.beginTransaction()

            // Replace the default fragment animations with animator resources
            // representing rotations when switching to the back of the card, as
            // well as animator resources representing rotations when flipping
            // back to the front (e.g. when the system Back button is pressed).
            .setCustomAnimations(
                R.anim.card_flip_right_in,
                R.anim.card_flip_right_out,
                R.anim.card_flip_left_in,
                R.anim.card_flip_left_out
            )

            // Replace any fragments currently in the container view with a
            // fragment representing the next page (indicated by the
            // just-incremented currentPage variable).
           // .replace(R.id.container, CardBackFragment())

            // Add this transaction to the back stack, allowing users to press
            // Back to get to the front of the card.
            .addToBackStack(null)

            // Commit the transaction.
            .commit()
    }

}