package com.example.wisielec

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay = findViewById<Button>(R.id.btn_play)
        val btnEnd = findViewById<Button>(R.id.btn_end)
        val editWord = findViewById<EditText>(R.id.edt_word)

        btnPlay.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            val word = editWord.text.toString()
            intent.putExtra("key", word)
            editWord.text = null
            startActivity(intent)
        }

        btnEnd.setOnClickListener {
            finishAffinity()
        }
    }
}
