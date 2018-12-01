package com.charan.virtuasaalbum.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList
import com.charan.virtuasaalbum.R
import com.charan.virtuasaalbum.ui.model.Album


class AlbumAdapter(
    cryptocurrencies: List<Album>?) : RecyclerView.Adapter<AlbumAdapter.CryptocurrencieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencieViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.album_list_item_constraint,
            parent, false)
        return CryptocurrencieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CryptocurrencieViewHolder, position: Int) {
        val cryptocurrencyItem = cryptocurrenciesList[position]
        holder?.cryptocurrencyListItem(cryptocurrencyItem)
    }

    private var cryptocurrenciesList = ArrayList<Album>()

  init {
    this.cryptocurrenciesList = cryptocurrencies as ArrayList<Album>
  }

  override fun getItemCount(): Int {
    return cryptocurrenciesList.size
  }

  fun addCryptocurrencies(cryptocurrencies: List<Album>){
    val initPosition = cryptocurrenciesList.size
    cryptocurrenciesList.addAll(cryptocurrencies)
    notifyItemRangeInserted(initPosition, cryptocurrenciesList.size)
  }

  class CryptocurrencieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var cryptocurrencyId = itemView.findViewById<TextView>(R.id.tvCryptoId)
    var cryptocurrencyTitle = itemView.findViewById<TextView>(R.id.tvCryptoTitle)

    fun cryptocurrencyListItem(cryptocurrencyItem: Album) {
      cryptocurrencyId.text = cryptocurrencyItem.userId.toString()
      cryptocurrencyTitle.text = cryptocurrencyItem.title
    }
  }
}