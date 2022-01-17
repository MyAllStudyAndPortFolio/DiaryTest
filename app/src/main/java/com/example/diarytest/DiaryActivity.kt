package com.example.diarytest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity:AppCompatActivity() {

    //메인 쓰레드에 연결됨
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?, ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val diaryEditText = findViewById<EditText>(R.id.diaryEditText)
        val returnButton = findViewById<Button>(R.id.returnButton)
        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreferences.getString("detail",""))



        val runnable = Runnable {
            getSharedPreferences("diary" , Context.MODE_PRIVATE).edit{
                putString("detail",diaryEditText.text.toString())
            }
            Log.d("DiaryActivity", "SAVE!! ${diaryEditText.text.toString()}")
        }
        returnButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        diaryEditText.addTextChangedListener {
            Log.d("DiaryActivity","Textchanged :: $it")
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)

        }
    }
}