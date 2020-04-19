package com.example.gamebacklog.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game

import kotlinx.android.synthetic.main.activity_game_log.*
import kotlinx.android.synthetic.main.content_game_log.*

class GameLog : AppCompatActivity() {
    private lateinit var gameLog: List<Game>
    private lateinit var BackLogAdapter: BackLogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_log)
        setSupportActionBar(toolbar)

        initView()
        initViewModel()
    }

    private fun initView() {
        rvGameBackLog.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGameBackLog.adapter = BackLogAdapter

        fab.setOnClickListener { view -> } // Start activity here
    }

    private fun initViewModel() {
    }


}
