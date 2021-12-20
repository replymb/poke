package com.demo.telepassdigitalandroid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.telepassdigitalandroid.R

class ImagesAdapter(private val data: List<String>) :
    RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {


    class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.imageListItem_imageView)
        fun bind(url: String) {
            Glide.with(itemView).load(url).fitCenter()
                .error(R.drawable.ic_round_error_outline_24)
                .placeholder(R.drawable.ic_round_hourglass_top_24).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}