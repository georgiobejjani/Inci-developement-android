package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.graphics.drawable.BitmapDrawable





class MainActivity : AppCompatActivity() {
    var nbdice:Int=1
    var count = 1
//    var emptydice =findViewById<ImageView>(R.id.emptydice)
//    var emptydice2 =findViewById<ImageView>(R.id.emptydice2)
//    var emptydice3 =findViewById<ImageView>(R.id.emptydice3)
//    var emptydice4 =findViewById<ImageView>(R.id.emptydice4)

    var imagesArray : MutableList<ImageView> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imagesArray.add(findViewById<ImageView>(R.id.emptydice))
        imagesArray.add(findViewById<ImageView>(R.id.emptydice2))
        imagesArray.add(findViewById<ImageView>(R.id.emptydice3))
        imagesArray.add(findViewById<ImageView>(R.id.emptydice4))
        nbdice=findViewById<TextView>(R.id.diceCounter).text.toString().toInt()
    }
    fun randomnumber():String
    {
        val numb=(1..6).random()
        return numb.toString()
    }

    fun rollnextimage(textarray: TextView, imagearray: ImageView)
    {
        val rollnext: ImageView =imagearray
        val dices=if(textarray.text as String == "-" ){R.drawable.emptydice}
        else{
            when((textarray.text as String).toInt()){
                1-> R.drawable.dice1
                2-> R.drawable.dice2
                3-> R.drawable.dice3
                4-> R.drawable.dice4
                5-> R.drawable.dice5
                else-> R.drawable.dice6
            }
        }
        rollnext.setImageResource((dices))
    }

        fun counter():String {
        var counts = 0

        when (count) {
            1 -> {
                counts = 1
            }
            2 -> {
                counts = 2
            }
            3 -> {
                counts = 3
            }
            4 -> {
                counts = 4
            }
        }
        return counts.toString()
        }


        fun btnSetClicked(sender: View) {


            for(i in 0 until nbdice)
            {

                    imagesArray[i].setImageResource(randomimage())
            }
            for (i in nbdice until 4)
            {
                imagesArray[i].setImageResource(R.drawable.emptydice)
            }
    }

        fun btnAddDice(sender: View) {
        var emptydice =findViewById<ImageView>(R.id.emptydice)
        var emptydice2 =findViewById<ImageView>(R.id.emptydice2)
        var emptydice3 =findViewById<ImageView>(R.id.emptydice3)
        var emptydice4 =findViewById<ImageView>(R.id.emptydice4)


        var mtv = findViewById<TextView>(R.id.diceCounter)
        count++
        var counter = count.toString()
        mtv.text = counter

        var Addbtn = findViewById<Button>(R.id.addDice)
        var Rembtn = findViewById<Button>(R.id.RemoveDice)

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
            nbdice=findViewById<TextView>(R.id.diceCounter).text.toString().toInt()

    }


        fun btnRemoveDice(sender: View) {

            var emptydice =findViewById<ImageView>(R.id.emptydice)
            var emptydice2 =findViewById<ImageView>(R.id.emptydice2)
            var emptydice3 =findViewById<ImageView>(R.id.emptydice3)
            var emptydice4 =findViewById<ImageView>(R.id.emptydice4)
            nbdice=findViewById<TextView>(R.id.diceCounter).text.toString().toInt()

            var mtv = findViewById<TextView>(R.id.diceCounter)
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

        fun randomimage():Int
        {
            when((1..6).random()){
                1-> return R.drawable.dice1
                2-> return R.drawable.dice2
                3-> return R.drawable.dice3
                4-> return R.drawable.dice4
                5-> return R.drawable.dice5
                6-> return R.drawable.dice6
            }
            return 0
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
                    imagesArray[i].setImageResource(randomimage())
                    return
                }
            }
        }
}
