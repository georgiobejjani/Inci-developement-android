package com.example.diceroller

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object
    {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var context: Context
    }

    var nbdice:Int=1
    var count = 1
    val imagesArray : MutableList<ImageView> = mutableListOf()
    val temparray : MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = applicationContext
        imagesArray.add(findViewById<ImageView>(R.id.emptydice))
        imagesArray.add(findViewById<ImageView>(R.id.emptydice2))
        imagesArray.add(findViewById<ImageView>(R.id.emptydice3))
        imagesArray.add(findViewById<ImageView>(R.id.emptydice4))
        nbdice=findViewById<TextView>(R.id.diceCounter).text.toString().toInt()
        createChannel()
    }

    fun randomimage(nbm:Int):Int
    {
        when(nbm){
            1-> return R.drawable.dice1
            2-> return R.drawable.dice2
            3-> return R.drawable.dice3
            4-> return R.drawable.dice4
            5-> return R.drawable.dice5
            6-> return R.drawable.dice6
        }
        return 0
    }


    fun btnSetClicked(sender: View) {
        val storeNumbers:MutableList<Int> = mutableListOf()
        for(i in 0 until nbdice)
        {
            val numbers=(1..6).random()
            storeNumbers.add(numbers)
            imagesArray[i].setImageResource(randomimage(numbers))
        }
        for (i in nbdice until 4)
        {
            imagesArray[i].setImageResource(R.drawable.emptydice)
        }
        ResultStored.addAll(dataResult(storeNumbers,nbdice))
    }

    fun btnAddDice(sender: View) {
        val emptydice =findViewById<ImageView>(R.id.emptydice)
        val emptydice2 =findViewById<ImageView>(R.id.emptydice2)
        val emptydice3 =findViewById<ImageView>(R.id.emptydice3)
        val emptydice4 =findViewById<ImageView>(R.id.emptydice4)


        val mtv = findViewById<TextView>(R.id.diceCounter)
        count++
        val counter = count.toString()
        mtv.text = counter
        nbdice=findViewById<TextView>(R.id.diceCounter).text.toString().toInt()

        val Addbtn = findViewById<Button>(R.id.addDice)
        val Rembtn = findViewById<Button>(R.id.RemoveDice)

        if(count==4)
        {
            Addbtn.isEnabled=false
        }
        if(count>1)
        {
            Rembtn.isEnabled=true
        }

        when (count) {
            2 -> {
                emptydice2.visibility = View.VISIBLE
                emptydice2.setImageResource(R.drawable.emptydice)
            }
            3 -> {
                emptydice3.visibility = View.VISIBLE
                emptydice3.setImageResource(R.drawable.emptydice)
            }
            4 -> {
                emptydice4.visibility = View.VISIBLE
                emptydice4.setImageResource(R.drawable.emptydice)
            }
        }


    }


        fun btnRemoveDice(sender: View) {

            var emptydice =findViewById<ImageView>(R.id.emptydice)
            val emptydice2 =findViewById<ImageView>(R.id.emptydice2)
            val emptydice3 =findViewById<ImageView>(R.id.emptydice3)
            val emptydice4 =findViewById<ImageView>(R.id.emptydice4)

            nbdice=findViewById<TextView>(R.id.diceCounter).text.toString().toInt()

            val mtv = findViewById<TextView>(R.id.diceCounter)
            count--
            var counter = count.toString()
            mtv.text = counter

            var Addbtn = findViewById<Button>(R.id.addDice)
            var Rembtn = findViewById<Button>(R.id.RemoveDice)

            if(count==1)
            {
                Rembtn.isEnabled=false
            }
            if(count<4)
            {
                Addbtn.isEnabled=true
            }

            when {
                count < 2 -> {
                    emptydice2.visibility = View.INVISIBLE
                }
                count < 3 -> {
                    emptydice3.visibility = View.INVISIBLE
                }
                count < 4 -> {
                    emptydice4.visibility = View.INVISIBLE
                }

            }
       }


        fun rollnextdice(Sender: View)
        {
            var ghe= (imagesArray[nbdice-1].getDrawable() as BitmapDrawable).bitmap
            if(!ghe.sameAs((getDrawable(R.drawable.emptydice) as BitmapDrawable).bitmap))
            {
                for (i in 0 until nbdice)
                {
                    imagesArray [i].setImageResource(R.drawable.emptydice)
                }
            }
            for(i in 0 until nbdice)
            {
                var gh= (imagesArray[i].getDrawable() as BitmapDrawable).bitmap
                if(gh.sameAs((getDrawable(R.drawable.emptydice) as BitmapDrawable).bitmap))
                {
                    val btnrandom=(1..6).random()
                    temparray.add(btnrandom)
                    if(temparray.size==nbdice){
                        ResultStored.storedNumber.add(dataResult(temparray.toList() as MutableList<Int>,nbdice))
                        post(temparray.toString())
                        temparray.clear()
                    }
                    imagesArray[i].setImageResource(randomimage(btnrandom))
                    return
                }
            }
        }
    fun ResultNumberBtn(Sender: View) {
        val intent = Intent(this, StoredNumber::class.java)
        startActivity(intent)
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
    fun camera(Sender: View) {
        val intent = Intent(this, camera::class.java)
        startActivity(intent)
    }
    fun maps(Sender: View) {
        val intent = Intent(this, maps::class.java)
        startActivity(intent)
    }

    private fun createChannel(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val name = "georgio"
            val descriptionText = "informative channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Notifpage.CHANNEL_ID,name,importance).apply{
                description = descriptionText
            }

            val notificaionManager:NotificationManager= getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificaionManager.createNotificationChannel(channel)
        }
    }


}
