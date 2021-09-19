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
//        var mtv = findViewById<TextView>(R.id.dice1)
//        var mtv2 = findViewById<TextView>(R.id.dice2)
//        var mtv3 = findViewById<TextView>(R.id.dice3)
//        var mtv4 = findViewById<TextView>(R.id.dice4)

//        var emptydice =findViewById<ImageView>(R.id.emptydice)
//        var emptydice2 =findViewById<ImageView>(R.id.emptydice2)
//        var emptydice3 =findViewById<ImageView>(R.id.emptydice3)
//        var emptydice4 =findViewById<ImageView>(R.id.emptydice4)
//
//        val numb1 = randomnumber()
//        val numb2 = randomnumber()
//        val numb3 = randomnumber()
//        val numb4 = randomnumber()
//
//
//       val dice1 = when(numb1.toInt())
//        {
//            1-> R.drawable.dice1
//            2-> R.drawable.dice2
//            3-> R.drawable.dice3
//            4-> R.drawable.dice4
//            5-> R.drawable.dice5
//            else-> R.drawable.dice6
//       }
//        emptydice.setImageResource(dice1)
//        if(emptydice2.visibility==View.VISIBLE)
//        {
//            val dice2 = when(numb2.toInt())
//            {
//                1-> R.drawable.dice1
//                2-> R.drawable.dice2
//                3-> R.drawable.dice3
//                4-> R.drawable.dice4
//                5-> R.drawable.dice5
//                else-> R.drawable.dice6
//            }
//            emptydice2.setImageResource(dice2)
//        }
//
//        if(emptydice3.visibility==View.VISIBLE)
//        {
//            val dice3 = when(numb3.toInt())
//            {
//                1-> R.drawable.dice1
//                2-> R.drawable.dice2
//                3-> R.drawable.dice3
//                4-> R.drawable.dice4
//                5-> R.drawable.dice5
//                else-> R.drawable.dice6
//            }
//            emptydice3.setImageResource(dice3)
//        }
//
//        if(emptydice4.visibility==View.VISIBLE)
//        {
//            val dice4 = when(numb4.toInt())
//            {
//                1-> R.drawable.dice1
//                2-> R.drawable.dice2
//                3-> R.drawable.dice3
//                4-> R.drawable.dice4
//                5-> R.drawable.dice5
//                else-> R.drawable.dice6
//            }
//            emptydice4.setImageResource(dice4)
//        }

//        if(mtv2.visibility == View.VISIBLE)
//        {
//           var text2 = numb2.toString()
//           mtv2.text=text2
//        }
//         if(mtv3.visibility == View.VISIBLE)
//        {
//            var text3 = numb3.toString()
//            mtv3.text=text3
//        }
//        if(mtv4.visibility == View.VISIBLE)
//        {
//            var text4 = numb4.toString()
//            mtv4.text=text4
//        }
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

    }


        fun btnRemoveDice(sender: View) {

            var emptydice =findViewById<ImageView>(R.id.emptydice)
            var emptydice2 =findViewById<ImageView>(R.id.emptydice2)
            var emptydice3 =findViewById<ImageView>(R.id.emptydice3)
            var emptydice4 =findViewById<ImageView>(R.id.emptydice4)

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
            var nbdice=findViewById<TextView>(R.id.diceCounter).text.toString().toInt()

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
//                Log.d("georgio",imageviewarray[i].drawable.toString() )
//                Log.d("georgio2",getDrawable(R.drawable.emptydice).toString())
                var gh= (imagesArray[i].getDrawable() as BitmapDrawable).bitmap
                if(gh.sameAs((getDrawable(R.drawable.emptydice) as BitmapDrawable).bitmap))
                {
                    //put new image
                    imagesArray[i].setImageResource(randomimage())
                    return
                }

            }
        }
}
