package com.uhc.my_albums.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.uhc.my_albums.R
import com.uhc.my_albums.R.layout
import com.uhc.my_albums.data.Album
import com.uhc.my_albums.utils.Constants
import com.uhc.my_albums.utils.InfiniteScrollListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_albums.*
import java.util.*
import javax.inject.Inject

class AlbumsActivity : AppCompatActivity() {

    @Inject
    lateinit var albumsViewModelFactory: AlbumsViewModelFactory
    var albumsAdapter = AlbumsAdapter(ArrayList())
    lateinit var albumsViewModel: AlbumsViewModel
    var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_albums)
        AndroidInjection.inject(this)

        initializeRecycler()

        albumsViewModel = ViewModelProviders.of(this, albumsViewModelFactory).get(
            AlbumsViewModel::class.java
        )

        progressBar.visibility = View.VISIBLE
        loadData()

        albumsViewModel.albumsResult().observe(this,
            Observer<List<Album>> {
                if (it != null) {
                    val position = albumsAdapter.itemCount
                    albumsAdapter.addAlbums(it)
                    recycler.adapter = albumsAdapter
                    recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
                }
            })

        albumsViewModel.albumsError().observe(this, Observer<String> {
            if (it != null) {
                Toast.makeText(
                    this, resources.getString(R.string.album_error_message) + it,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        albumsViewModel.albumsLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
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
        albumsViewModel.loadAlbums(Constants.LIMIT, currentPage * Constants.OFFSET)
        currentPage++
    }

    override fun onDestroy() {
        albumsViewModel.disposeElements()
        super.onDestroy()
    }
}
