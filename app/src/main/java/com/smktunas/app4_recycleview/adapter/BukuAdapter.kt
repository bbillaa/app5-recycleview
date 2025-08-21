package com.smktunas.app4_recycleview.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smktunas.app4_recycleview.R
import com.smktunas.app4_recycleview.model.Buku
import com.smktunas.app4_recycleview.DetailActivity

class BukuAdapter(
    private val context: Context,
    private val listBuku: List<Buku>
) : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>() {


    inner class BukuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudul)
        val tvPenulis: TextView = itemView.findViewById(R.id.tvPenulis)
        val tvTahun: TextView = itemView.findViewById(R.id.tvTahun)
        val ivCover: ImageView = itemView.findViewById(R.id.ivCover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_buku, parent, false)
        return BukuViewHolder(view)
    }

    override fun getItemCount(): Int = listBuku.size

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val buku = listBuku[position]
        holder.tvJudul.text = buku.judul
        holder.tvPenulis.text = buku.penulis
        holder.tvTahun.text = buku.tahun.toString()

        Glide.with(holder.itemView.context)
            .load(buku.cover)
            .into(holder.ivCover)


        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Memilih buku: ${buku.judul}", Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(context)
                .setTitle("Lihat Detail Buku?")
                .setMessage("Ingin melihat detail dari buku \"${buku.judul}\"?")
                .setPositiveButton("Lihat") { _, _ ->
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("judul", buku.judul)
                    intent.putExtra("penulis", buku.penulis)
                    intent.putExtra("tahun", buku.tahun)
                    intent.putExtra("cover", buku.cover)
                    context.startActivity(intent)
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }
}
