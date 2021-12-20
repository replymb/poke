package com.demo.telepassdigitalandroid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.telepassdigitalandroid.R
import com.demo.telepassdigitalandroid.network.model.Poke
import java.util.*

val POKE_COMPARATOR = object : DiffUtil.ItemCallback<Poke>() {
    override fun areItemsTheSame(oldItem: Poke, newItem: Poke): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Poke, newItem: Poke): Boolean =
        oldItem.name == newItem.name
}

class PokeListAdapter(private val onItemClick: (item: Poke) -> Unit) :
    PagingDataAdapter<Poke, PokeListAdapter.PokeViewHolder>(POKE_COMPARATOR) {


    class PokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.pokeListItemName_textView)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.pokeListItemThumbnail_imageView)
        private val loading = itemView.findViewById<View>(R.id.pokeListItemLoading_progressBar)

        fun bind(poke: Poke?, onItemClick: (item: Poke) -> Unit) {

            if (poke == null ) {
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE
            }

            poke?.let { p ->
                itemView.setOnClickListener {
                    onItemClick.invoke(p)
                }
            }
            name.text =
                poke?.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            val thumbnailUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${poke?.num}.png";
            Glide.with(itemView)
                .load(thumbnailUrl)
                .centerInside()
                .error(R.drawable.ic_round_error_outline_24)
                .placeholder(R.drawable.ic_round_hourglass_top_24)
                .into(thumbnail)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        return PokeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_poke, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }


/*
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<Poke>) {
        this.data = data
        notifyDataSetChanged()
    }*/
}