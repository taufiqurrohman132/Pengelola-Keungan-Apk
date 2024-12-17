package com.example.myapppengelolakeungan.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapppengelolakeungan.R

class HomeTransaksiAdapter(private val data: Array<String>) :
    RecyclerView.Adapter<HomeTransaksiAdapter.ViewItemTransaksiHolder>() {

    class ViewItemTransaksiHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewItemTransaksiHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_transaksi_item, parent, false)
        return ViewItemTransaksiHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewItemTransaksiHolder,
        position: Int
    ) {
    }

    override fun getItemCount(): Int = data.size

    }