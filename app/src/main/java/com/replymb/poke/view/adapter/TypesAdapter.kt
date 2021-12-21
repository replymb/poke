package com.replymb.poke.view.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.replymb.poke.R
import com.replymb.poke.utils.ColorUtils
import java.util.*

class TypesAdapter(private val data: List<String>) :
    RecyclerView.Adapter<TypesAdapter.TypesViewHolder>() {


    class TypesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val type = itemView.findViewById<TextView>(R.id.itemType_textView)
        fun bind(text: String) {
            type.text = text.uppercase(Locale.getDefault())
            type.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(itemView.resources, ColorUtils.forType(text), null))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewHolder {
        return TypesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_type, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TypesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}