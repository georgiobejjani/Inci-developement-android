package com.example.diceroller

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

object ResultStored {
     val storedNumber: MutableList<dataResult> = mutableListOf()
    fun add_collection(x: dataResult)
    {
        storedNumber.add(x)
        post(x.Numberstored.toString())
    }

    fun add_partial(dice: Int, nbDice: Int)
    {
        if (storedNumber.size == 0)
        {
            val tmp_list : MutableList<Int> = mutableListOf(dice)
            storedNumber.add(dataResult(tmp_list, nbDice))
            return
        }

        val last_element = storedNumber.removeLast()

        if (last_element.Numberstored.size == last_element.Numbers)
        {
            storedNumber.add(last_element)

            val tmp_list : MutableList<Int> = mutableListOf(dice)
            storedNumber.add(dataResult(tmp_list, nbDice))
        }
        else
        {
            last_element.Numberstored.add(dice)
            last_element.Numbers = nbDice
            storedNumber.add(last_element)

            if (last_element.Numberstored.size == last_element.Numbers)
            {
                post(last_element.Numbers.toString())
            }

        }
    }

    fun get_all() : MutableList<dataResult>
    {
        return storedNumber;
    }

    fun size(): Int
    {
        return storedNumber.size
    }


    private fun post(data: String)
    {
        val queue : RequestQueue = Volley.newRequestQueue(MainActivity.context)
        val url = "https://615f752af7254d0017068157.mockapi.io/resultss"

        Log.d("hh", data)

        val postRequest = JsonObjectRequest(
            Request.Method.POST, url,
            JSONObject("{ dice : $data }"),
            {
                    response -> Log.d("hi", response.toString())
            },
            {
                    error -> Log.d("bye", error.toString())
            })

        queue.add(postRequest)
    }

}