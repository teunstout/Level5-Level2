package com.example.gamebacklog.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.gamebacklog.R

import kotlinx.android.synthetic.main.activity_game_log.*

class GameLog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_log)
        setSupportActionBar(toolbar)

        initView()
        initViewHolder()

    }

    private fun initView() {
        fab.setOnClickListener { view ->
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
    }

    private fun initViewHolder() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
