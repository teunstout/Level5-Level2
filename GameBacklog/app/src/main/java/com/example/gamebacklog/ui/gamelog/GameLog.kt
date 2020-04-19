package com.example.gamebacklog.ui.gamelog

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game

import kotlinx.android.synthetic.main.activity_game_log.*
import kotlinx.android.synthetic.main.content_game_log.*

class GameLog : AppCompatActivity() {
    private val gameLogViewModel: GameLogViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_log)
        setSupportActionBar(toolbar)

        initView()
        initViewModel()
    }

    private fun initView() {
        fab.setOnClickListener { } // Start activity here
    }

    private fun initViewModel() {
    }


}
