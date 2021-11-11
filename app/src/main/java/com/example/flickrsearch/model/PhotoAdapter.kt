package com.example.flickrsearch.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrsearch.R
import com.example.flickrsearch.data.Photo
import com.squareup.picasso.Picasso

class PhotoAdapter(val photos: MutableList<Photo> = mutableListOf()) : RecyclerView.Adapter<PhotoAdapter.PhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photo,
                parent,
                false
            )
        )
    }

    fun updateList(photo: MutableList<Photo>) {
        photos.clear()
        photos.addAll(photo)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo) {
            showPhoto(photo)
            showTitle(photo)
        }

        private fun showPhoto(photo: Photo) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
            Picasso.get().
            load(photo.url)
                .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                .centerCrop()
                .into(imageView)
        }

        private fun showTitle(photo: Photo) {
            val textView: TextView = itemView.findViewById(R.id.textView)
            textView.text = photo.title
        }
    }
}

const val IMAGE_SIDE_PX = 400