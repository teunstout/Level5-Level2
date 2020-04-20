package com.example.gamebacklog.ui.add_game

import android.os.Build
import android.os.Bundle
import android.os.Message
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game

import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import java.lang.Error
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*

class AddGame : AppCompatActivity() {

    private val addGameViewModel: AddGameViewModel by viewModels()
//    private val AMOUNT_OF_MONTHS = 12
//    private val AMOUNT_OF_DAYS = 31
//    private val MAX_YEAR = 2100
//    private val ALL_MONTHS = 12
//    private val ALL_DAYS = 31


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            if (checkInputFields()) {
                addGameViewModel.insertGame(
                    Game(
                        etTitle.text.toString(),
                        etPlatform.text.toString(),
                        // string
                    Date().toString()
                    )
                )
                finish()
            }
        }
    }

    private fun checkInputFields(): Boolean {
        // Check if title is empty or null
        if (etTitle.text.isNullOrEmpty()) {
            toastMessage(R.string.wrong_title)
            return false
        }

        // Check if the date is empty or null
        if (etYear.text.isNullOrEmpty() || etMonth.text.isNullOrEmpty() || etDay.text.isNullOrEmpty()) {
            toastMessage(R.string.wrong_date)
            return false
        }
        return true
    }

    private fun toastMessage(messageInt: Int) {
        Toast.makeText(this, messageInt, Toast.LENGTH_SHORT).show()
    }

    private fun toastMessageS(messageInt: String) {
        Toast.makeText(this, messageInt, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
