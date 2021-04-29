package com.example.wisielec

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.text.StringBuilder

class GameActivity : AppCompatActivity() {
    val imgResources = arrayOf(
        R.drawable.ic_h0,
        R.drawable.ic_h1,
        R.drawable.ic_h2,
        R.drawable.ic_h3,
        R.drawable.ic_h4,
        R.drawable.ic_h5,
        R.drawable.ic_h6,
        R.drawable.ic_h7,
        R.drawable.ic_h8,
        R.drawable.ic_h9
    )

    val chars = "abcdefghijklmnoprstuqwxyz"
    var stage = 0
    var end = false
    private lateinit var keyWord: String
    private lateinit var img: ImageView
    private lateinit var tvWord: TextView
    private lateinit var tvTmp: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val stringBuilder = StringBuilder()

        keyWord = Intent().getStringExtra("key").toString()
        img = findViewById(R.id.img_hangman)
        tvWord = findViewById(R.id.txt_word)
        tvTmp = findViewById(R.id.txt_tmp)
        tvWord.text = stringBuilder
        tvTmp.text = "Len = ${keyWord.length}"

        for (i in 0..keyWord.length) {
            stringBuilder.append("*")
        }

        val buttonThree = findViewById<Button>(R.id.btn_end_hma)

        buttonThree.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
