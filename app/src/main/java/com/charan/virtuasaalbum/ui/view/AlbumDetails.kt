package com.charan.virtuasaalbum.ui.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.charan.virtuasaalbum.R
import com.charan.virtuasaalbum.ui.model.Album
import kotlinx.android.synthetic.main.activity_album_details.*

class AlbumDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        val album: Album = intent.getSerializableExtra("ALBUM") as Album

        tvAlbumId.text = "Album ID: " + album.id
        tvAlbumUserId.text = "Album User ID: " + album.userId
        tvAlbumTitle.text = "Album Title: " + album.title

    }
}
