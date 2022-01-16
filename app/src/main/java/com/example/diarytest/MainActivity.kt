package com.example.diarytest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            // if password success
            if (passwordPreferences.getString("password","000").equals(passwordFromUser))
            {
               // startActivity(intent)

                //TODO must write diary pages
            }
            // if password failure
            else
            {
                AlertDialog.Builder(this)
                    .setTitle("failure!")
                    .setMessage("wrong password. plz type again")
            }
        }
    }

    private val openButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openButton)
    }

    private val changePasswordButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply{
                minValue=0
                maxValue=9
            }
    }

    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply{
                minValue=0
                maxValue=9
            }
    }

    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply{
                minValue=0
                maxValue=9
            }
    }

}