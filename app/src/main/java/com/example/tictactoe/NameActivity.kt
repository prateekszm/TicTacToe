package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*

  private var PLAYER1:String?=null
  private var PLAYER2:String?=null
    const val KEY1="Player1"
    const val KEY2="Player2"

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        button2.setOnClickListener {

            if (etPlayer1.text.toString().isEmpty() && etPlayer2.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter The Player Names", Toast.LENGTH_SHORT).show()
            } else {
                intent = Intent(this, PlayingBoard::class.java)
                PLAYER1 = etPlayer1.text.toString()
                PLAYER2 = etPlayer2.text.toString()

                val array= arrayOf(PLAYER1, PLAYER2)
                intent.putExtra(KEY1,array)
//                intent.putExtra(KEY1, PLAYER1)
//                intent.putExtra(KEY2, PLAYER2)
                startActivity(intent)

            }
        }
    }
}
