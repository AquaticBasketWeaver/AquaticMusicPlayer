package com.aquaticbasketweaver.aquaticmusicplayer

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

object MediaPlayerSingleton {
    private const val TAG = "MediaPlayerSingleton"

    val mediaPlayer = MediaPlayer()
    var currentSongId: String? = null

    private fun startMediaPlayerWithUri (context: Context, id: String) {
        val uri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
        mediaPlayer.setDataSource(context, uri)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    // When the play/pause button is pressed
    // Requires that a song has been chosen
    fun playPressed() {
        if (currentSongId != null) {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            } else {
                mediaPlayer.start()
            }
        } else {
            Log.e(TAG, "playPressed: No song selected?")
        }
    }

    // When a song is selected in a list
    fun chooseSong(context: Context, songId: String) {
        currentSongId = songId
        mediaPlayer.reset()
        startMediaPlayerWithUri(context, songId)
    }
}