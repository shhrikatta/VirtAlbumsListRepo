package com.charan.virtuasaalbum

import com.charan.virtuasaalbum.ui.model.Album

interface OnItemClickListener {
    fun onItemClicked(album: Album)
}