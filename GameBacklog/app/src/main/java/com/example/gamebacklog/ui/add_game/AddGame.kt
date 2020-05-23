package com.example.gamebacklog.ui.add_game

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.ui.gamelog.GameLog

import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import java.lang.Error
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddGame : AppCompatActivity() {

    private val addGameViewModel: AddGameViewModel by viewModels()
    private var dateRelease = ""
    private val pattern = DateTimeFormatter.ofPattern(GameLog.PATTERN_DATE)

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
                        dateRelease
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

        // check if date is good
        try {
            val year = etYear.text.toString()
            var month = etMonth.text.toString()
            var day = etDay.text.toString()
            var dateString = ""

            if (etMonth.text.toString().length == 1) {
                month = "0$month"
            }

            if (etDay.text.toString().length == 1) {
                day = "0$day"
            }

            dateString = "$year-$month-$day"
            dateRelease = LocalDate.parse(dateString).format(pattern)
        } catch (e: Error) {
            toastMessage(R.string.wrong_date)
            return false
        }

        return true
    }

    private fun toastMessage(messageInt: Int) {
        Toast.makeText(this, messageInt, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
