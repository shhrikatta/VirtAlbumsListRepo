package com.charan.virtuasaalbum.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.charan.virtuasaalbum.OnItemClickListener
import java.util.ArrayList
import com.charan.virtuasaalbum.R
import com.charan.virtuasaalbum.ui.model.Album


class AlbumAdapter(
    listAlbums: List<Album>?, val onItemClickListener: (Album) -> Unit) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.album_list_item_constraint,
            parent, false)
        return AlbumViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val albumItem: Album = listAlbums[position]

        holder.item.setOnClickListener {
            onItemClickListener(albumItem)
        }

        holder?.albumListItem(albumItem)
    }

    private var listAlbums = ArrayList<Album>()

  init {
    this.listAlbums = listAlbums as ArrayList<Album>
  }

  override fun getItemCount(): Int {
    return listAlbums.size
  }

  fun addAlbums(listAlbums: List<Album>){
    val initPosition = listAlbums.size
    this.listAlbums.addAll(listAlbums)
    notifyItemRangeInserted(initPosition, listAlbums.size)
  }

  class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var albumId = itemView.findViewById<TextView>(R.id.tvAlbumId)
    var albumTitle = itemView.findViewById<TextView>(R.id.tvAlbumTitle)
    var item = itemView.findViewById<CardView>(R.id.cardView)

    fun albumListItem(albumItem: Album) {
      albumId.text = albumItem.userId.toString()
      albumTitle.text = albumItem.title
    }
  }
}