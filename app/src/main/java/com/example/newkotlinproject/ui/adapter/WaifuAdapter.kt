package com.example.newkotlinproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newkotlinproject.R
import com.example.newkotlinproject.model.Waifu
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.waifu_recycler_line.view.*

class WaifuAdapter(val theWaifus : ArrayList<Waifu>) : RecyclerView.Adapter<WaifuAdapter.WaifuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaifuViewHolder {
        return WaifuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.waifu_recycler_line, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return theWaifus.size
    }

    override fun onBindViewHolder(holder: WaifuViewHolder, position: Int) {
        val currentWaifu = theWaifus[position]

        Picasso.get().load(currentWaifu.photo).into(holder.photo)

        holder.name.text = currentWaifu.name
        holder.source.text = currentWaifu.source
    }

    class WaifuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photo = itemView.waifuListImage
        var name = itemView.waifuListName
        var source = itemView.waifuListSource
    }
}