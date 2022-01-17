package com.example.diarytest

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {

            if(changePasswordMode){
                Toast.makeText(this,"your are doing changing password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            // if password success
            if (passwordPreferences.getString("password","000").equals(passwordFromUser))
            {
               startActivity(Intent(this,DiaryActivity::class.java))
            }
            // if password failure
            else
            {
                showErrorAlertDialog()
            }
        }
        changePasswordButton.setOnClickListener {


            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
            val passwordPreferences = getSharedPreferences("password",Context.MODE_PRIVATE)

            if(changePasswordMode){

             passwordPreferences.edit(commit = true){


                 putString("password", passwordFromUser)
             }

                changePasswordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)

            }
            else{
                //changePasswordMode activated :: checking password is right or not


                // if password success
                if (passwordPreferences.getString("password","000").equals(passwordFromUser))
                {
                    changePasswordMode = true
                    Toast.makeText(this,"please type changing password",Toast.LENGTH_SHORT).show()

                    changePasswordButton.setBackgroundColor(Color.RED)
                }
                // if password failure
                else
                {
                    showErrorAlertDialog()
                }


            }
        }
    }
    private fun showErrorAlertDialog(){
        AlertDialog.Builder(this)
            .setTitle("failure!")
            .setMessage("wrong password. plz type again")
            .setPositiveButton("check"){ _, _ ->}
            .create()
            .show()
    }

    private var changePasswordMode = false;

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