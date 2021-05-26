package com.aquaticbasketweaver.aquaticmusicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val FULL_SCREEN_PLAYER_MESSAGE = "com.aquaticbasketweaver.aquaticmusicplayer.FULL_SCREEN_PLAYER_MESSAGE"

class SongItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

class SongAdapter: RecyclerView.Adapter<SongItemViewHolder>() {
    var data = listOf<SongFileGetter.SongInfo>()

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: SongItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.title
        holder.textView.setOnClickListener(View.OnClickListener {
            Log.d("DEBUG", "clicked thing is: ${item.title}")
            val intent = Intent(holder.textView.context, FullScreenPlayerActivity::class.java).apply {
                putExtra(FULL_SCREEN_PLAYER_MESSAGE, "stuff")
            }
            startActivity(holder.textView.context, intent, null)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.song_item_view, parent, false) as TextView
        return SongItemViewHolder(view)
    }

}

class SongScrollingActivity : AppCompatActivity() {
    private val songFileGetter = SongFileGetter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_scrolling)

        val songInfoList = songFileGetter.getAllSongs(this, contentResolver)

        val scrollType = intent.getStringExtra(SONG_MESSAGE)
        actionBar.apply {
            title = scrollType
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Recycler view has off screen things unloaded and can pick between list, grid, etc.
        val recyclerView = findViewById<RecyclerView>(R.id.songRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val songAdapter = SongAdapter()
        songAdapter.data = songInfoList
        recyclerView.adapter = songAdapter
    }
}