package com.aquaticbasketweaver.aquaticmusicplayer

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

const val SONG_MESSAGE = "com.aquaticbasketweaver.aquaticmusicplayer.SONG_TEXT"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
//        supportActionBar?.title = "test"

        if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
        }
    }

    fun startSongScrollingActivity(view: View) {
        val buttonText = findViewById<Button>(view.id)
        val intent = Intent(this, SongScrollingActivity::class.java).apply {
            putExtra(SONG_MESSAGE, buttonText.text)
        }
        startActivity(intent)
    }
}