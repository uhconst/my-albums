package com.uhc.my_albums.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.uhc.my_albums.R
import com.uhc.my_albums.data.Album
import com.uhc.my_albums.ui.list.AlbumsAdapter.AlbumsViewHolder
import java.util.*


class AlbumsAdapter(
    albums: List<Album>?
) : RecyclerView.Adapter<AlbumsViewHolder>() {

    private var albumsList = ArrayList<Album>()

    init {
        this.albumsList = albums as ArrayList<Album>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.album_list_item,
            parent, false
        )
        return AlbumsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val albumItem = albumsList[position]
        holder.albumListItem(albumItem)
    }

    fun addAlbums(albums: List<Album>) {
        val initPosition = albumsList.size
        albumsList.addAll(albums)
        notifyItemRangeInserted(initPosition, albumsList.size)
    }

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var albumId = itemView.findViewById<TextView>(R.id.album_id)

        fun albumListItem(albumItem: Album) {
            albumId.text = albumItem.title
        }
    }
}
