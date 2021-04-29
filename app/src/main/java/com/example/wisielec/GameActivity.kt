package com.example.wisielec

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.* // ktlint-disable no-wildcard-imports
import androidx.appcompat.app.AppCompatActivity
import java.lang.NullPointerException
import java.util.* // ktlint-disable no-wildcard-imports
import kotlin.text.StringBuilder

class GameActivity : AppCompatActivity() {
    private val imgResources = arrayOf(
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

    private val chars = "abcdefghijklmnoprstuqwxyz"
    private var stage = 0
    private var end = false
    private lateinit var keyWord: String
    private lateinit var img: ImageView
    private lateinit var tvWord: TextView
    private lateinit var tvTmp: TextView
    private lateinit var tableRow: TableRow

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val stringBuilder = StringBuilder()

        keyWord = intent.getStringExtra("key").toString()
        img = findViewById(R.id.img_hangman)

        for (i in keyWord.indices) {
            stringBuilder.append("*")
        }

        tvWord = findViewById(R.id.txt_word)
        tvTmp = findViewById(R.id.txt_tmp)
        tvWord.text = stringBuilder
        tvTmp.text = "Len = $keyWord"

        val buttonThree = findViewById<Button>(R.id.btn_end_hma)

        setButtons()

        buttonThree.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setButtons() {
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)

        for (i in chars.indices) {
            val char = chars[i]
            val button = Button(this)
            button.text = char.toString().toUpperCase(Locale.ROOT)
            button.textSize = 20F
            button.id = i
            button.setOnClickListener {
                letterAction(char)
            }
            tableRow = tableLayout.getChildAt(i / 5) as TableRow
            tableRow.addView(button)
        }
    }

    private fun letterAction(char: Char) {
        if (end) return
        val index = keyWord.indexOf(char)
        val isHiddenLetter = tvWord.text.toString().indexOf('*') == -1
        val isLastStage = stage == imgResources.size - 1

        if (isHiddenLetter) win()
        if (isLastStage) lose()
        if (index < 0) drawNextHangManElement()
        else showLetter(char)
    }

    @SuppressLint("SetTextI18n")
    private fun lose() {
        tvTmp.text = "You Lose!"
        img.setImageResource(R.drawable.ic_lose)
    }

    @SuppressLint("SetTextI18n")
    private fun win() {
        tvTmp.text = "You Win!"
        img.setImageResource(R.drawable.ic_win)
    }

    private fun showLetter(char: Char) {
        val tv = tvWord.text.toString().toCharArray()
        for (i in tv.indices) {
            if (keyWord[i] == char) tv[i] = char
        }
        tvWord.text = String(tv)
    }

    private fun drawNextHangManElement() {
        if (stage < imgResources.size - 1) {
            stage++
            img.setImageResource(imgResources[stage])
        }
    }
}
