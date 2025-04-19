package com.example.androidactivityfragments

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidactivityfragments.adapters.GameAdapter
import com.example.androidactivityfragments.models.Game
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.time.Duration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val gamesList = loadFromJson()
        val adapter = GameAdapter(gamesList, this) { selectedItem ->
            openFragment(selectedItem)
        }
        recyclerView.adapter = adapter
    }

    private fun loadFromJson(): List<Game> {
        val json: String
        val inputStream = resources.openRawResource(R.raw.games)
        json = inputStream.bufferedReader().use { it.readText() }

        val gson = Gson()
        val gamesListType = object : TypeToken<List<Game>>() {}.type
        return gson.fromJson(json, gamesListType)
    }

    private fun openFragment(game: Game) {
        val fragment = GameDetailsFragment()
        val bundle = Bundle()
        bundle.putString("TITLE", game.title)
        bundle.putInt("YEAR", game.releaseYear)
        bundle.putInt("RATING", game.rating)
        bundle.putString("POSTER", game.posterUrl)
        bundle.putString("DESCR", game.description)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}