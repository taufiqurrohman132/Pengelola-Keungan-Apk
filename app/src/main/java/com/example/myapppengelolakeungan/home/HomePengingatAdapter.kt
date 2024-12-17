package com.example.myapppengelolakeungan.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapppengelolakeungan.R

class HomePengingatAdapter(private val data: Array<String>) :
    RecyclerView.Adapter<HomePengingatAdapter.ViewItemPengingatHolder>() {

    class ViewItemPengingatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewItemPengingatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_pengingat_pembayaran_item, parent, false)
        return ViewItemPengingatHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewItemPengingatHolder,
        position: Int
    ) {
    }

    override fun getItemCount(): Int = data.size
}