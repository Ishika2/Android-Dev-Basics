package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//This activity allows users to roll a dice
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //This activates my app when the user opens it
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button2)  //this finds the id of the button
        rollButton.setOnClickListener { rollDice() }  //this listens on tap/click on the button
    }

    //This rolls the dice/calls Dice class
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val luckyNum = 6
//        val resultTextView: TextView = findViewById(R.id.textView)
//        resultTextView.text = diceRoll.toString()
        val diceImage: ImageView = findViewById(R.id.imageView)
//        if(diceRoll == luckyNum)
//        {
//            val toast = Toast.makeText(this, "Congo!!", Toast.LENGTH_SHORT)
//            toast.show()
//        }
//        else
//        {
//            val toast = Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT)
//            toast.show()
//        }
//        when(diceRoll)
//        {
//            luckyNum -> Toast.makeText(this, "Congo!!", Toast.LENGTH_SHORT).show()
//            1 -> Toast.makeText(this, "Try Again!!", Toast.LENGTH_SHORT).show()
//            2 -> Toast.makeText(this, "Try Again!!", Toast.LENGTH_SHORT).show()
//            3 -> Toast.makeText(this, "Try Again!!", Toast.LENGTH_SHORT).show()
//            4 -> Toast.makeText(this, "Try Again!!", Toast.LENGTH_SHORT).show()
//            5 -> Toast.makeText(this, "Try Again!!", Toast.LENGTH_SHORT).show()
//        }
//        when (diceRoll) {
//            1 -> diceImage.setImageResource(R.drawable.dice_1)
//            2 -> diceImage.setImageResource(R.drawable.dice_2)
//            3 -> diceImage.setImageResource(R.drawable.dice_3)
//            4 -> diceImage.setImageResource(R.drawable.dice_4)
//            5 -> diceImage.setImageResource(R.drawable.dice_5)
//            6 -> diceImage.setImageResource(R.drawable.dice_6)
//        }
        val drawableResource = when (diceRoll)
        {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRoll.toString()
//        val diceRoll2 = dice.roll()
//        val resultTextView: TextView = findViewById(R.id.textView)
//        val resultTextView2: TextView = findViewById(R.id.textView2)
//        resultTextView.text = diceRoll.toString()
//        resultTextView2.text = diceRoll2.toString()
//        diceRoll.toString()
//        diceRoll2.toString()
        val toast = Toast.makeText(this, "$diceRoll on Dice", Toast.LENGTH_SHORT)
//        val toast = Toast.makeText(this, "$diceRoll on Dice1 and $diceRoll2 on Dice2", Toast.LENGTH_SHORT)
        toast.show() //Toast shows a pop-up message for short period of time, drawback - these are rate limited so avoid using it
    }
}

//This chooses a random number to be selected
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}