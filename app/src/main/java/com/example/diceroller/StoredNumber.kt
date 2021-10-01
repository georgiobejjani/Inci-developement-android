package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.tabs.TabLayout

class StoredNumber : AppCompatActivity() {
    lateinit var linearLayoutHistory : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stored_number)

        linearLayoutHistory = findViewById<LinearLayout>(R.id.layoutbtnresult)

        val all_dices =  ResultStored.storedNumber

        for (dices in all_dices) {
            val text = TextView(this)
            text.textSize = 20f
            text.text = dices.Numberstored.toString()

            val card = CardView(this)
            card.addView(text)
            //card.setCardBackgroundColor(getColor(R.color.white))
            card.radius = 20F
            card.useCompatPadding = true

            linearLayoutHistory.addView(card)
        }

    }

    fun delete_All(sender: View) {
        ResultStored.storedNumber.clear()
        linearLayoutHistory.removeAllViews()
    }
}