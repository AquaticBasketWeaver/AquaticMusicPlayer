package com.aquaticbasketweaver.aquaticmusicplayer

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton

class FullScreenPlayerActivity : AppCompatActivity() {
    private var songData: SongFileGetter.SongInfo? = null

    private fun togglePlayButtonImage() {
        val playImageButton = findViewById<ImageButton>(R.id.playerPlayButton)
        val player = MediaPlayerSingleton.mediaPlayer
        if (player.isPlaying) {
            val pauseDrawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                resources.getDrawable(R.drawable.outline_pause_24, null)
            } else {
                resources.getDrawable(R.drawable.outline_pause_24)
            }
            playImageButton.setImageDrawable(pauseDrawable)
        } else {
            val playDrawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                resources.getDrawable(R.drawable.outline_play_arrow_24, null)
            } else {
                resources.getDrawable(R.drawable.outline_pause_24)
            }
            playImageButton.setImageDrawable(playDrawable)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_player)

        togglePlayButtonImage()

        songData = intent.getSerializableExtra(FULL_SCREEN_PLAYER_MESSAGE) as? SongFileGetter.SongInfo
        Log.d("DEBUG", "onCreate: ${songData?.title}")
    }

    fun onPlayClick(view: View) {
        MediaPlayerSingleton.playPressed()
        togglePlayButtonImage()
    }
}