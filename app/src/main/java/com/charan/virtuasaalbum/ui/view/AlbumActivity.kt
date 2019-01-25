package com.charan.virtuasaalbum.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.charan.virtuasaalbum.OnItemClickListener
import com.charan.virtuasaalbum.R
import com.charan.virtuasaalbum.adapter.AlbumAdapter
import com.charan.virtuasaalbum.ui.model.Album
import com.charan.virtuasaalbum.ui.viewmodel.AlbumViewModel
import com.charan.virtuasaalbum.ui.viewmodelfactory.AlbumViewModelFactory
import com.charan.virtuasaalbum.utils.Constants
import com.charan.virtuasaalbum.utils.InfiniteScrollListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_album.*
import java.util.ArrayList
import javax.inject.Inject

class AlbumActivity : AppCompatActivity(), OnItemClickListener {

    override fun onItemClicked(album: Album) {
        albumViewModel.loadAlbumDetails(album.id)
    }

    @Inject
    lateinit var albumViewModelFactory: AlbumViewModelFactory

    var albumAdapter = AlbumAdapter(ArrayList())    {
        albumViewModel.loadAlbumDetails(it.id)
    }

    lateinit var albumViewModel: AlbumViewModel
    var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        AndroidInjection.inject(this)

        initializeRecycler()

        albumViewModel = ViewModelProviders.of(this, albumViewModelFactory).get(
            AlbumViewModel::class.java)

        loadData()

        albumViewModel.albumResult().observe(this,
            Observer<List<Album>> {
                if (it != null) {
                    val position = albumAdapter.itemCount
                    albumAdapter.addAlbums(it)
                    recycler.adapter = albumAdapter
                    recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
                }
            })

        albumViewModel.albumError().observe(this, Observer<String> {
            if (it != null) {
                Toast.makeText(this, resources.getString(R.string.error_message) + it,
                    Toast.LENGTH_SHORT).show()
            }
        })

        albumViewModel.albumLoader().observe(this, Observer<Boolean> {
            if (it!!) {
                progressBar.visibility = View.VISIBLE
            } else{
                progressBar.visibility = View.GONE
            }
        })

        albumViewModel.albumDetailsResult().observe(this, Observer<Album>   {
            Toast.makeText(this, it?.title, Toast.LENGTH_LONG).show()
        })
    }

    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScrollListener({ loadData() }, gridLayoutManager))
        }
    }

    fun loadData() {
        albumViewModel.loadAlbum(Constants.LIMIT, currentPage * Constants.OFFSET)
        currentPage++
    }

    override fun onDestroy() {
        albumViewModel.disposeElements()
        super.onDestroy()
    }
}
