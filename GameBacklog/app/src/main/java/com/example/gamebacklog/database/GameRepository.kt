package com.example.gamebacklog.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.Game

class GameRepository(context: Context) {

    private var gameInterface: GameInterface

    init {
        val database = GameDatabase.getDatabase(context)
        gameInterface = database!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> = gameInterface.getAllGames()

    suspend fun insertGame(game: Game) = gameInterface.insertGame(game)

    suspend fun deleteGame(game: Game) = gameInterface.deleteGame(game)

    suspend fun deleteAllGames() = gameInterface.deleteAllGames()


}