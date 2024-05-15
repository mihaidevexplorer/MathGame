package com.techmania.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var addition : Button
    lateinit var subtraction : Button
    lateinit var multi : Button
    lateinit var divide :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.buttonAdd)
        subtraction = findViewById(R.id.buttonSub)
        multi = findViewById(R.id.buttonMulti)
        divide = findViewById(R.id.buttonDivide)

        addition.setOnClickListener() {
            val intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("operation", "addition")
            startActivity(intent)
        }
        subtraction.setOnClickListener(){
            val intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("operation", "subtraction")

            startActivity(intent)
        }
        multi.setOnClickListener(){
            val intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("operation", "multi")

            startActivity(intent)

        }
        divide.setOnClickListener(){
            val intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("operation", "divide")
            startActivity(intent)

        }
    }
}