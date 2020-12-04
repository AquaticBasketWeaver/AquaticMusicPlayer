package com.example.aquaticmusicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SongAdapter: RecyclerView.Adapter<SongItemViewHolder>() {
    var data = listOf<String>()
        // May need this piece of code later if list doesn't update
//        set(value) {
//            data = value
//            notifyDataSetChanged()
//        }

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: SongItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.song_item_view, parent, false) as TextView
        return SongItemViewHolder(view)
    }

}

class SongScrollingActivity : AppCompatActivity() {

    // TODO: make this actually get songs from a file
    private fun getSongs(): List<String> {
        val songs = mutableListOf<String>()
        for (i in 1..100) {
            songs.add("Song ${i}")
        }
        return songs
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_scrolling)

        val scrollType = intent.getStringExtra(SONG_MESSAGE)
        actionBar.apply {
            title = scrollType
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Recycler view has off screen things unloaded and can pick between list, grid, etc.
        val recyclerView = findViewById<RecyclerView>(R.id.songRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val songAdapter = SongAdapter()
        songAdapter.data = getSongs()
        recyclerView.adapter = songAdapter

        // Add songs to the song list
//        for (i in 1..50) {
//            val buttonView = Button(this).apply {
//                text = "Song ${i}"
//                height = 56
//                setOnClickListener {
//                    println("TODO: select the song to play, and go into activity")
//                }
//            }
//            songList.addView(buttonView)
//        }
    }
}