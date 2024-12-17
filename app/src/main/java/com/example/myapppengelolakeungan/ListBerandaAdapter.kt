package com.example.myapppengelolakeungan

import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListBerandaAdapter(private val items: ArrayList<Parcelable>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val VIEW_PENGELUARAN = 1
        const val VIEW_KABAR_FROM_SERATUS = 2
        const val VIEW_PENGINGAT_PEMBAYARAN = 3
        const val VIEW_TRANSAKSI_TERAKHIR = 4
        const val VIEW_ANGGARAN_KAMU = 5
//        const val VIEW_TUTORIAL = 5
        const val VIEW_TUJUAN_KEUANGAN = 6
        const val VIEW_UNDANG_TEMAN = 7
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            VIEW_PENGELUARAN -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_pengeluaran, parent, false)
                PengeluaranViewHolder(view)
            }
            VIEW_KABAR_FROM_SERATUS -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_kabar, parent, false)
                KabarViewHolder(view)
            }
            VIEW_TRANSAKSI_TERAKHIR -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_transaksi_terakhir, parent, false)
                TransaksiTerakhirViewHolder(view)
            }
            VIEW_ANGGARAN_KAMU -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_anggaran_kamu, parent, false)
                AnggaranViewHolder(view)
            }
            VIEW_TUJUAN_KEUANGAN -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_tujuan_keuangan, parent, false)
                TujuanKeuanganViewHolder(view)
            }
            VIEW_UNDANG_TEMAN -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_undang, parent, false)
                UndangTemanViewHolder(view)
            }
            VIEW_PENGINGAT_PEMBAYARAN -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_pengingat, parent, false)
                PengingatPembayaranViewHolder(view)
            }
            else -> throw IllegalArgumentException("invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PengeluaranViewHolder -> {
                val item = items[position] as Pengeluaran
                holder.bind(item)
            }
            is KabarViewHolder -> {
                val item = items[position] as Kabar
                holder.bind(item)
            }
            is TransaksiTerakhirViewHolder -> {
                val item = items[position] as TransaksiTerakhir
                holder.bind(item)
            }
            is TujuanKeuanganViewHolder -> {
                val item = items[position] as TujuanTabungan
                holder.bind(item)
            }
            is PengingatPembayaranViewHolder -> {
                val item = items[position] as PengingatPembayaran
                holder.bind(item)
            }
            is UndangTemanViewHolder -> {
                val item = items[position] as UndangTeman
                holder.bind(item)
            }
            is AnggaranViewHolder -> {
                val item = items[position] as AturAnggaran
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //untuk menentukan view type berdasarkan data
    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is Pengeluaran -> VIEW_PENGELUARAN
            is Kabar -> VIEW_KABAR_FROM_SERATUS
            is TransaksiTerakhir -> VIEW_TRANSAKSI_TERAKHIR
            is TujuanTabungan -> VIEW_TUJUAN_KEUANGAN
            is AturAnggaran -> VIEW_ANGGARAN_KAMU
            is UndangTeman -> VIEW_UNDANG_TEMAN
            is PengingatPembayaran -> VIEW_PENGINGAT_PEMBAYARAN
            else -> {
                Log.i("posisi", "getItemViewType: $position")
                throw IllegalArgumentException("Invalid Type Of Data")
            }
        }
    }

    class PengeluaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item : Pengeluaran){

        }
    }
    class KabarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Kabar){

        }
    }
    class TransaksiTerakhirViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TransaksiTerakhir){

        }
    }
    class TujuanKeuanganViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TujuanTabungan){

        }
    }
    class AnggaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AturAnggaran){

        }
    }
    class UndangTemanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: UndangTeman){

        }
    }
    class PengingatPembayaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PengingatPembayaran){

        }
    }

}

