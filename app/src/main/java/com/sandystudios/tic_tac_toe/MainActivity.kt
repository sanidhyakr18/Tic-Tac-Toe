package com.sandystudios.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var board: Array<Array<Button>>
    var boardStatus = Array(3) { IntArray(3) }
    var turnCount = 0
    var PLAYER = true
    var winStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

        initialiseBoardStatus()

        resetBtn.setOnClickListener {
            turnCount = 0
            PLAYER = true
            winStatus = false
            initialiseBoardStatus()
            textView.text = "PLAYER X TURN !"
        }
    }

    private fun initialiseBoardStatus() {
        for (i in board) {
            for (button in i) {
                button.isEnabled = true
                button.text = ""
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1
            }
        }
    }

    override fun onClick(v: View) {
        when (v) {
            button1 -> {
                updateValue(0, 0, PLAYER)
            }
            button2 -> {
                updateValue(0, 1, PLAYER)
            }
            button3 -> {
                updateValue(0, 2, PLAYER)
            }
            button4 -> {
                updateValue(1, 0, PLAYER)
            }
            button5 -> {
                updateValue(1, 1, PLAYER)
            }
            button6 -> {
                updateValue(1, 2, PLAYER)
            }
            button7 -> {
                updateValue(2, 0, PLAYER)
            }
            button8 -> {
                updateValue(2, 1, PLAYER)
            }
            button9 -> {
                updateValue(2, 2, PLAYER)
            }
        }
        checkWinner()
        if(winStatus) {
            disableButtons()
        } else {
            PLAYER = !PLAYER
            turnCount++
            if (PLAYER)
                textView.text = "PLAYER X TURN !"
            else
                textView.text = "PLAYER O TURN !"
            if (turnCount == 9)
                textView.text = "GAME DRAW !"
        }
    }

    private fun disableButtons() {
        for (i in board)
            for (button in i)
                button.isEnabled = false
    }

    private fun checkWinner() {
        //horizontal
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    textView.text = "PLAYER X WINS !"
                    winStatus = true
                } else if (boardStatus[i][0] == 0) {
                    textView.text = "PLAYER O WINS !"
                    winStatus = true
                }
                break
            }
        }

        //vertical
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    textView.text = "PLAYER X WINS !"
                    winStatus = true
                } else if (boardStatus[0][i] == 0) {
                    textView.text = "PLAYER O WINS !"
                    winStatus = true
                }
                break
            }
        }

        //diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                textView.text = "Player X WINS !"
                winStatus = true
            } else if (boardStatus[0][0] == 0) {
                textView.text = "PLAYER O WINS !"
                winStatus = true
            }
        }

        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                textView.text = "PLAYER X WINS !"
                winStatus = true
            } else if (boardStatus[0][2] == 0) {
                textView.text = "PLAYER O WINS !"
                winStatus = true
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0
        board[row][col].text = text
        boardStatus[row][col] = value
        board[row][col].isEnabled = false
    }

}