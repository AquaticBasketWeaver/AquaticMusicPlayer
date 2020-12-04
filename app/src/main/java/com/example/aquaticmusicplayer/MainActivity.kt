package com.example.aquaticmusicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.Toolbar

const val SONG_MESSAGE = "com.example.aquaticmusicplayer.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
//        supportActionBar?.title = "test"
    }

    fun startSongScrollingActivity(view: View) {
        val buttonText = findViewById<Button>(view.id)
        val intent = Intent(this, SongScrollingActivity::class.java).apply {
            putExtra(SONG_MESSAGE, buttonText.text)
        }
        startActivity(intent)
    }
}