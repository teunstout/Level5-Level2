package com.example.gamebacklog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gamebacklog.model.Game

@TypeConverters(DateConverter::class)
@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameDatabase(): RoomDatabase() {

    abstract fun gameDao(): GameInterface // Use the interface to make the note app

    // In companion object to create a singleton
    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE" // database name

        @Volatile // Every action is immediately visible to all.
        private var INSTANCE: GameDatabase? = null // Make instance of this database

        fun getDatabase(context: Context): GameDatabase? {
            if (INSTANCE == null) {
                synchronized(GameDatabase::class.java) {
                    // make database synchronized so that data is not edited at the same time. Data won't go faulty now.
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder( // if there is no instance make a instance
                            context.applicationContext,
                            GameDatabase::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}