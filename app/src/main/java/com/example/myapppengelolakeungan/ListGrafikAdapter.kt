package com.example.myapppengelolakeungan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListGrafikAdapter : RecyclerView.Adapter<ListGrafikAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_pengeluaran_item_grafik, parent, false)//ketika attack toroot true maka akan parent yang di attack
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 90
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

    }
}