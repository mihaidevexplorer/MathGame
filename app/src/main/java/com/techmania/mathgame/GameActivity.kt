package com.techmania.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var textScore: TextView
    lateinit var textLife: TextView
    lateinit var textTime: TextView


    lateinit var textQuestion: TextView
    lateinit var editTextAnswer: EditText

    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3
    lateinit var timer: CountDownTimer
    private val startTimerMillis: Long = 60000
    var timeLeftInMillis: Long = startTimerMillis
    var operation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        operation = intent.getStringExtra("operation").toString()
        supportActionBar!!.title = when (operation) {
            "addition" -> "Addition"
            "subtraction" -> "Subtraction"
            "multi" -> "Multiplication"
            "divide" -> "Division"
            else -> "Game"
        }

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()
        editTextAnswer.setText("")




        buttonOk.setOnClickListener() {

            val input = editTextAnswer.text.toString()
            if (input == "") {
                Toast.makeText(
                    applicationContext, "Please write an answer or click the next button",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                pouseTimer()
                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer) {
                    userScore = userScore + 10
                    textQuestion.text = "Congratulations, your answer is corect"
                    textScore.text = userScore.toString()

                } else {
                    userLife--
                    textQuestion.text = "Sorry your answer is wrong"
                    textLife.text = userLife.toString()
                }
            }


        }
        buttonNext.setOnClickListener() {
            pouseTimer()
            resetTimer()

            editTextAnswer.setText("")

            if (userLife == 0) {
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()

            } else {
                gameContinue()

            }


        }
    }

    fun gameContinue() {
        when (operation) {
            "addition" -> {
                val number1 = Random.nextInt(0, 100)
                val number2 = Random.nextInt(0, 100)
                textQuestion.text = "$number1 + $number2"
                correctAnswer = number1 + number2
            }
            "subtraction" -> {
                val number3 = Random.nextInt(0, 100)
                var number4 = Random.nextInt(0, number3 + 1)
                textQuestion.text = "$number3 - $number4"
                correctAnswer = number3 - number4
            }
            "multi" -> {
                val number5 = Random.nextInt(0, 100)
                val number6 = Random.nextInt(0, 100)
                textQuestion.text = "$number5 * $number6"
                correctAnswer = number5 * number6
            }
            "divide" -> {
                val number7 = Random.nextInt(0, 100)
                val number8 = Random.nextInt(0, number7 +1)
                textQuestion.text = "$number7 / $number8"
                correctAnswer = number7 / number8
            }
            else -> {
                // Tratează cazul în care operația nu este validă
            }
        }
        startTimer()



    }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntillFinished: Long) {

                timeLeftInMillis = millisUntillFinished
                updateText()

            }

            override fun onFinish() {

                pouseTimer()
                resetTimer()
                updateText()

                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, Time is up ! "

            }
        }.start()
    }

    fun updateText() {
        val remainingTime: Int = (timeLeftInMillis / 1000).toInt()
        textTime.text = String.format(Locale.getDefault(), "%02d", remainingTime)

    }

    fun pouseTimer() {
        timer.cancel()

    }

    fun resetTimer() {
        timeLeftInMillis = startTimerMillis
        updateText()

    }

}