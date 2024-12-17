package com.example.myapppengelolakeungan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class AnggaranKamuKategoriAdapter(private val dataItemKamuKategori: ArrayList<String>, private val img: ArrayList<Int>) : RecyclerView.Adapter<AnggaranKamuKategoriAdapter.ViewAnggaranKamuKategoriHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAnggaranKamuKategoriHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anggaran_kamu_kategori_item, parent, false)
        return ViewAnggaranKamuKategoriHolder(view)
    }

    override fun getItemCount(): Int = dataItemKamuKategori.size

    override fun onBindViewHolder(holder: ViewAnggaranKamuKategoriHolder, position: Int) {
        val text = dataItemKamuKategori[position]
        val img = img[position]
        holder.text.text = text
        holder.img.setImageResource(img)
    }

    class ViewAnggaranKamuKategoriHolder(item: View) : RecyclerView.ViewHolder(item){
        val text = item.findViewById<TextView>(R.id.tv_anggaran_kamu_item)
        val img = item.findViewById<ShapeableImageView>(R.id.shap_anggaran_kamu_item)
    }
}

class AnggaranKamuItemAdapter(private val dataItem: ArrayList<String>) : RecyclerView.Adapter<AnggaranKamuItemAdapter.ViewAnggaranKamuItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAnggaranKamuItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anggaran_fragment_kamu_item, parent, false)
        return ViewAnggaranKamuItemHolder(view)
    }

    override fun getItemCount(): Int = dataItem.size

    override fun onBindViewHolder(holder: ViewAnggaranKamuItemHolder, position: Int) {
    }

    class ViewAnggaranKamuItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

