package com.example.gamebacklog.ui.gamelog

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.ui.add_game.AddGame
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_game_log.*
import kotlinx.android.synthetic.main.content_game_log.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GameLog : AppCompatActivity() {

    private var games = arrayListOf<Game>()
    private val gameLogViewModel: GameLogViewModel by viewModels()
    private val backLogAdapter = BackLogAdapter(games)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_log)
        setSupportActionBar(toolbar)

        initView()
        initViewModel()
    }

    private fun initView() {
        rvGameBackLog.layoutManager =
            LinearLayoutManager(this@GameLog, LinearLayoutManager.VERTICAL, false)
        rvGameBackLog.adapter = backLogAdapter
        createItemTouchHelper().attachToRecyclerView(rvGameBackLog)

        fab.setOnClickListener {
            val intent = Intent(this, AddGame::class.java)
            startActivity(intent)
        }
    }

    private fun initViewModel() {
        gameLogViewModel.gameData
            .observe(this,
                Observer { gameListDatabase ->
                    games.clear()

                    if (gameListDatabase.isNullOrEmpty()) games.addAll(gameListDatabase as ArrayList<Game>)
                    else games.addAll(sortedList(gameListDatabase))
                    backLogAdapter.notifyDataSetChanged()
                }
            )
    }

    private fun sortedList(games: List<Game>): List<Game> {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_DATE)
        return games.sortedByDescending { LocalDate.parse(it.releaseDate, dateTimeFormatter) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_game_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all_items -> {
                val deletedGames = ArrayList<Game>()
                deletedGames.addAll(games)
                gameLogViewModel.deleteAllGames()

                Snackbar.make(findViewById(R.id.rvGameBackLog), getString(R.string.delete_game, "games"), Snackbar.LENGTH_SHORT)
                    .setAction("UNDO"){
                        deletedGames.forEach { gameLogViewModel.insertGame(it) }
                    }.show()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedGame = games[position]

                gameLogViewModel.deleteGame(games[position])

                Snackbar.make(viewHolder.itemView, getString(R.string.delete_game, deletedGame.title), Snackbar.LENGTH_SHORT)
                    .setAction("UNDO"){
                        gameLogViewModel.insertGame(deletedGame)
                    }.show()
            }
        }
        return ItemTouchHelper(callback)
    }
    companion object{
        const val PATTERN_DATE = "d MMMM yyyy"
    }
}
