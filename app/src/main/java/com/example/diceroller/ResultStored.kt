package com.example.diceroller

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

object ResultStored {
     val storedNumber: MutableList<dataResult> = mutableListOf()
    fun addAll(x: dataResult)
    {
        storedNumber.add(x)
        post(x.Numberstored.toString())
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