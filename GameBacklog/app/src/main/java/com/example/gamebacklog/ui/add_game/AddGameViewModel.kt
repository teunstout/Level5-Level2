package com.example.gamebacklog.ui.add_game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.database.GameRepository
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGameViewModel(application: Application) : AndroidViewModel(application) {
    private val gameRepository = GameRepository(application.applicationContext)
    private val coroutine = CoroutineScope(Dispatchers.IO)

    fun insertGame(game: Game) {
        coroutine.launch {
                gameRepository.insertGame(game)
        }
    }
}