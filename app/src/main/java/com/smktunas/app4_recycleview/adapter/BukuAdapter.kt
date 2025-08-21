package com.smktunas.app4_recycleview.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smktunas.app4_recycleview.R
import com.smktunas.app4_recycleview.model.Buku
import com.smktunas.app4_recycleview.utils.DetailActivity

class  BukuAdapter(
    private val context: Context,
    private val bukuList: MutableList<Buku>
) : RecyclerView.Adapter<BukuAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudul)
        val tvPenulis: TextView = itemView.findViewById(R.id.tvPenulis)
        val tvTahun: TextView = itemView.findViewById(R.id.tvTahun)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_buku, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = bukuList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val buku = bukuList[position]
        holder.tvJudul.text = buku.judul
        holder.tvPenulis.text = buku.penulis
        holder.tvTahun.text = buku.tahunTerbit

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Memilih buku: ${buku.judul}", Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(context)
                .setTitle("Lihat Detail Buku?")
                .setMessage("Ingin melihat detail dari buku \"${buku.judul}\"?")
                .setPositiveButton("Lihat") { _, _ ->
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("judul", buku.judul)
                    intent.putExtra("penulis", buku.penulis)
                    intent.putExtra("tahun", buku.tahunTerbit)
                    context.startActivity(intent)
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }
}
