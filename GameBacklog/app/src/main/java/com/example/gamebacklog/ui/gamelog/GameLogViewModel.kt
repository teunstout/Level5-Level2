package com.example.gamebacklog.ui.gamelog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.database.GameRepository

class GameLogViewModel(application: Application) : AndroidViewModel(application){

    val repository = GameRepository(application.applicationContext)

}