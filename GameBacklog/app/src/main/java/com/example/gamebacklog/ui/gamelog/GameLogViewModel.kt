package com.example.gamebacklog.ui.gamelog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.database.GameRepository
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameLogViewModel(application: Application) : AndroidViewModel(application){
    private val repository = GameRepository(application.applicationContext)
    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun getAllData(): LiveData<List<Game>>{
        return repository.getAllGames()
    }

    fun deleteGame(game: Game){
        coroutine.launch {
            repository.deleteGame(game)
        }
    }

    fun deleteAllGames(){
        coroutine.launch {
            repository.deleteAllGames()
        }
    }

    fun insertGame(game: Game) {
        coroutine.launch {
            repository.insertGame(game)
        }
    }
}