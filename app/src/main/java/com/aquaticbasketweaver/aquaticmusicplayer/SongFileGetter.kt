package com.aquaticbasketweaver.aquaticmusicplayer

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat


class SongFileGetter {
    data class SongInfo(val title: String, val artist: String, val album: String, val uri: Uri)

    fun getAllSongs (context : Context, contentResolver: ContentResolver): List<SongInfo> {
        val songInfoList: MutableList<SongInfo> = mutableListOf()

        val selectData : Array<String> = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media._ID
        )
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val cursor : Cursor? = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            selectData,
            selection,
            null,
            MediaStore.Audio.Media.IS_MUSIC
        )
        if (cursor != null) {
            Log.d("DEBUG", "Cursor is NOT null")
            while (cursor.moveToNext()) {
                val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                val album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                val uri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
                Log.d("DEBUG", "title $title \n " +
                        "artist $artist \n " +
                        "album $album \n " +
                        "id $id \n " +
                        "URI $uri")
                songInfoList.add(SongInfo(title, artist, album, uri))
            }
            cursor.close()
        } else {
            Log.d("DEBUG", "Cursor is null")
        }
        Log.d("DEBUG", songInfoList.toString())
        return songInfoList
    }
}