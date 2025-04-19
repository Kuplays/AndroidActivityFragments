package com.example.androidactivityfragments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidactivityfragments.R
import com.example.androidactivityfragments.models.Game

class GameAdapter(private val gamesList: List<Game>, context: Context, private val onItemClick: (Game) -> Unit) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    private val ctx = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_details, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = gamesList[position]
        holder.gameTitleView.text = game.title
        holder.gameReleaseYear.text = ctx.getString(R.string.gameReleaseYear, game.releaseYear.toString())
        holder.gameRating.text = ctx.getString(R.string.gameRating, game.rating.toString())
        holder.gameDescr.text = game.description

        val imgId = ctx.resources.getIdentifier(game.posterUrl, "drawable", ctx.packageName)
        if (imgId != 0) {
            holder.posterUrl.setImageResource(imgId)
        }
        else {
            holder.posterUrl.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    override fun getItemCount(): Int = gamesList.size

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameTitleView: TextView = itemView.findViewById(R.id.id_game_title)
        val posterUrl: ImageView = itemView.findViewById(R.id.id_game_image)
        val gameReleaseYear: TextView = itemView.findViewById(R.id.id_game_release_year)
        val gameRating: TextView = itemView.findViewById(R.id.id_game_rating)
        val gameDescr: TextView = itemView.findViewById(R.id.id_game_descr)
        var posterId = 0

        init {
            itemView.setOnClickListener {
                onItemClick(gamesList[adapterPosition])
            }
        }
    }

}