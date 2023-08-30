package com.example.game

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess

class GameActivity : Activity() {

    var turn_first = "X"
    var turn_now = "X"

    var score1 = 0
    var score2 = 0

    var field = mutableListOf<Button>()

    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var btn9 : Button

    lateinit var tv1 : TextView
    lateinit var tv2 : TextView
    lateinit var tvscore : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)

        btn1 = findViewById(R.id.a1)
        btn2 = findViewById(R.id.a2)
        btn3 = findViewById(R.id.a3)
        btn4 = findViewById(R.id.b1)
        btn5 = findViewById(R.id.b2)
        btn6 = findViewById(R.id.b3)
        btn7 = findViewById(R.id.c1)
        btn8 = findViewById(R.id.c2)
        btn9 = findViewById(R.id.c3)

        field.add(btn1)
        field.add(btn2)
        field.add(btn3)
        field.add(btn4)
        field.add(btn5)
        field.add(btn6)
        field.add(btn7)
        field.add(btn8)
        field.add(btn9)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        tvscore = findViewById(R.id.score)

        tv1.setBackgroundColor(resources.getColor(R.color.yellow))
        tv2.setBackgroundColor(resources.getColor(R.color.transparent))
    }

    fun btnClick(view: View) {
        if (view !is Button) {
            return
        }

        if (view.text != "")
            return

        if(turn_now == "X")
        {
            view.text = "X"
            turn_now = "O"
            tv1.setBackgroundColor(resources.getColor(R.color.transparent))
            tv2.setBackgroundColor(resources.getColor(R.color.yellow))

        } else {
            view.text = "O"
            turn_now = "X"
            tv1.setBackgroundColor(resources.getColor(R.color.yellow))
            tv2.setBackgroundColor(resources.getColor(R.color.transparent))
        }

        if (checkField("X")) {
            ++score1
            showRes("X win!")
        } else if (checkField("O")) {
            ++score2
            showRes("O win!")
        } else {
            if (checkDraw()) {
                showRes("Draw!")
            }
        }
        tvscore.text = "$score1 : $score2"
    }

    private fun showRes(s: String) {
        val message = "\nPlayer X: $score1\n\nPlayer O: $score2\n\nDo you want to continue the game?"
        AlertDialog.Builder(this)
            .setTitle(s)
            .setMessage(message)
            .setPositiveButton("Yes") {
                _,_ -> resetBoard()
            }
            .setNegativeButton("No") {
                _,_ -> finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for(btn in field) {
            btn.text = ""
        }

        if (turn_first == "X") {
            turn_first = "O"
            tv1.setBackgroundColor(resources.getColor(R.color.transparent))
            tv2.setBackgroundColor(resources.getColor(R.color.yellow))
        } else {
            turn_first = "X"
            tv1.setBackgroundColor(resources.getColor(R.color.yellow))
            tv2.setBackgroundColor(resources.getColor(R.color.transparent))
        }

        turn_now = turn_first

    }

    private fun checkDraw(): Boolean {
        for(btn in field) {
            if(btn.text == "") {
                return false
            }
        }
        return true
    }

    private fun checkField(s: String): Boolean {
        if ((btn1.text == s && btn2.text == s && btn3.text == s) ||
            (btn4.text == s && btn5.text == s && btn6.text == s) ||
            (btn7.text == s && btn8.text == s && btn9.text == s) ||
            (btn1.text == s && btn4.text == s && btn7.text == s) ||
            (btn2.text == s && btn5.text == s && btn8.text == s) ||
            (btn3.text == s && btn6.text == s && btn9.text == s) ||
            (btn1.text == s && btn5.text == s && btn9.text == s) ||
            (btn3.text == s && btn5.text == s && btn7.text == s)) {
            return true
        } else {
            return false
        }
    }

    fun backClick(view: View) {
        AlertDialog.Builder(this)
            .setTitle("Are you sure you want to end the game?")
            .setMessage("Your score will be reset")
            .setPositiveButton("Yes") {
                _,_ -> finish()
            }
            .setNegativeButton("No") {
                dialog, id -> dialog.cancel()
            }
            .setCancelable(false)
            .show()
    }


}