package com.example.myapppengelolakeungan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Beranda(
    val naem: String
): Parcelable

@Parcelize
data class Pengeluaran(
    val pengeluaran: String,

): Parcelable

@Parcelize
data class Kabar(
    val naem: String
): Parcelable

@Parcelize
data class PengingatPembayaran(
    val naem: String
): Parcelable

@Parcelize
data class TransaksiTerakhir(
    val naem: String
): Parcelable

@Parcelize
data class AturAnggaran(
    val naem: String
): Parcelable

@Parcelize
data class TujuanTabungan(
    val naem: String
): Parcelable

@Parcelize
data class UndangTeman(
    val naem: String
): Parcelable



