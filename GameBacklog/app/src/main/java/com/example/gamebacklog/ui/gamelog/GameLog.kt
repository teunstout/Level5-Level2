package com.example.gamebacklog.ui.gamelog

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.ui.add_game.AddGame

import kotlinx.android.synthetic.main.activity_game_log.*
import kotlinx.android.synthetic.main.content_game_log.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class GameLog : AppCompatActivity() {

    private var games = arrayListOf<Game>(Game("","", Date()))
    private val gameLogViewModel: GameLogViewModel by viewModels()
    private val backLogAdapter = BackLogAdapter(games)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_log)
        setSupportActionBar(toolbar)

        initView()
        initViewModel()
    }

    private fun initView() {
        rvGameBackLog.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGameBackLog.adapter = backLogAdapter

        fab.setOnClickListener {
            val intent = Intent(this, AddGame::class.java)
            startActivity(intent)
        }
    }

    private fun initViewModel() {
        gameLogViewModel.getAllData()
            .observe(this,
                Observer { gameListDatabase ->
                games = gameListDatabase as ArrayList<Game>
                backLogAdapter.notifyDataSetChanged()
        })
    }


}
