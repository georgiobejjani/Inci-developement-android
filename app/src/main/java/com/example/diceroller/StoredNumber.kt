package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import org.json.JSONArray

class StoredNumber : AppCompatActivity()
{
    lateinit var linearLayoutHistory : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stored_number)

        linearLayoutHistory = findViewById<LinearLayout>(R.id.layoutbtnresult)
        val all_dices = ResultStored.get_all()

        get()

        Log.d("list: ", all_dices.toString())

        show(all_dices)

    }
    private fun get()
    {
        val list: MutableList<dataResult> = mutableListOf()

        val queue : RequestQueue = Volley.newRequestQueue(MainActivity.context)
        val url = "https://615f752af7254d0017068157.mockapi.io/resultss"


        val postRequest = JsonArrayRequest(
            Request.Method.GET, url,
            null,
            {
                    response ->
                for (i in 0 until response.length() - ResultStored.size())
                {
                    val jsonObject = response.getJSONObject(i)
                    val diceArray: JSONArray = jsonObject.getJSONArray("dice")
                    val diceMutableList: MutableList<Int> = mutableListOf()
                    for (j in 0 until diceArray.length())
                    {
                        diceMutableList.add(diceArray.get(j) as Int)
                    }
                    list.add(dataResult(diceMutableList, diceMutableList.size))
                }

                show(list)
            },
            {
                    error -> Log.d("geterror", error.toString())
            })

        queue.add(postRequest)
    }

    private fun show(list: MutableList<dataResult>) {
        for (dices in list) {
            val text = TextView(this)
            text.textSize = 20f
            text.text = dices.Numberstored.toString()

            val card = CardView(this)
            card.addView(text)

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