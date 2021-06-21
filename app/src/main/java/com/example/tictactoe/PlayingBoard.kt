package com.example.tictactoe

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_palying_board.*

class PlayingBoard : AppCompatActivity(), View.OnClickListener {

    var PLAYER=true
    var TURN_COUNT=0

    var boardStatus=Array(3){IntArray(3)}

    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palying_board)
        val playerArray=intent.getStringArrayExtra(KEY1)
        val player1= playerArray?.get(0)
        val player2= playerArray?.get(1)
        tvWhoIsWinner.text="Player X Turn"


//        if (player1.toString().isNotEmpty()){
//            Toast.makeText(this, "Arrived Secussfully ${player1.toString()}", Toast.LENGTH_SHORT).show()
//        }

        board= arrayOf(
            arrayOf(btn00,btn01,btn02),
            arrayOf(btn10,btn11,btn12),
            arrayOf(btn20,btn21,btn22)
        )

        for (i in board){
            for (button in i){
                button.setOnClickListener  (this)
            }
        }

        initializeBoardStatus()

        btnPlayAgain.setOnClickListener {
            PLAYER=true
            TURN_COUNT=0
            initializeBoardStatus()
            tvWhoIsWinner.text=""
        }

        btnHome.setOnClickListener {
            intent= Intent(this,NameActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun initializeBoardStatus() {

        for (i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""

            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View) {
        when(v.id){
            R.id.btn00->{
                updateValue(row=0,col=0,player=PLAYER)

            }
            R.id.btn01->{
                updateValue(row=0,col=1,player=PLAYER)

            }
            R.id.btn02->{
                updateValue(row=0,col=2,player=PLAYER)

            }
            R.id.btn10->{
                updateValue(row=1,col=0,player=PLAYER)

            }
            R.id.btn11->{
                updateValue(row=1,col=1,player=PLAYER)

            }
            R.id.btn12->{
                updateValue(row=1,col=2,player=PLAYER)

            }
            R.id.btn20->{
                updateValue(row=2,col=0,player=PLAYER)

            }
            R.id.btn21->{
                updateValue(row=2,col=1,player=PLAYER)

            }
            R.id.btn22->{
                updateValue(row=2,col=2,player=PLAYER)

            }
        }
        TURN_COUNT++
        PLAYER=!PLAYER

        if(PLAYER){
            displayInformation("Player X Turn")
        }else{
            displayInformation("Player 0 Turn")
        }
        if (TURN_COUNT==9){
            displayInformation("Draw Click Restart")
        }

        checkWinner()
    }

    private fun checkWinner() {
        //Horizontal Rows
        for (i in 0..2){
            if (boardStatus[i][0]==boardStatus[i][1]&&boardStatus[i][0]==boardStatus[i][2]){
                if (boardStatus[i][0]==1){
                    updateDisplay("Player X is Winner")
                    break
                }else if (boardStatus[i][0]==0){
                    updateDisplay("Player 0 is Winner")
                    break
                }
            }
        }
        //Vertical Columns
        for (i in 0..2){
            if (boardStatus[0][i]==boardStatus[1][i]&&boardStatus[0][i]==boardStatus[2][i]){
                if (boardStatus[0][i]==1){
                    updateDisplay("Player X is Winner")
                    break
                }else if (boardStatus[0][i]==0){
                    updateDisplay("Player 0 is Winner")
                    break
                }
            }
        }
        //First Diagonal

            if (boardStatus[0][0]==boardStatus[1][1]&&boardStatus[0][0]==boardStatus[2][2]){
                if (boardStatus[0][0]==1){
                    updateDisplay("Player X is Winner")
                }else if (boardStatus[0][0]==0){
                    updateDisplay("Player 0 is Winner")
                }
            }

        //Second Diagonal

        if (boardStatus[0][2]==boardStatus[1][1]&&boardStatus[0][2]==boardStatus[2][0]){
            if (boardStatus[0][2]==1){
                updateDisplay("Player X is Winner")
            }else if (boardStatus[0][2]==0){
                updateDisplay("Player 0 is Winner")
            }
        }


    }

    private fun updateDisplay(s: String) {
            tvWhoIsWinner.text=s
        for (i in board){
            for (button in i){
                button.isEnabled=false
            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.M)
    private fun displayInformation(text: String) {
        tvWhoIsWinner.apply {
            tvWhoIsWinner.text=text
            when (text) {
                "Player X Turn" -> {
                    setTextColor(getColor(R.color.XColor))
                }
                "Player 0 Turn" -> {
                    setTextColor(getColor(R.color.YColor))
                }
                else -> {
                    setTextColor(getColor(R.color.design_default_color_primary_variant))
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val textX0:String=if(player) "x" else "0"
        val value:Int=if (player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            text=textX0
            textSize=30f
            if (textX0=="x"){
                setTextColor(getColor(R.color.XColor))
            }else{
                setTextColor(getColor(R.color.YColor))
            }

        }
        boardStatus[row][col]=value
    }


}