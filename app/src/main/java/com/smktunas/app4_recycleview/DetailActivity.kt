package com.smktunas.app4_recycleview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_buku)

        val tvJudul: TextView = findViewById(R.id.tvJudul)
        val tvPenulis: TextView = findViewById(R.id.tvPenulis)
        val tvTahun: TextView = findViewById(R.id.tvTahun)

        val judul = intent.getStringExtra("judul") ?: "Tidak ada judul"
        val penulis = intent.getStringExtra("penulis") ?: "Tidak ada penulis"
        val tahun = intent.getStringExtra("tahun") ?: "Tidak ada tahun"
        val ivCover: ImageView =findViewById(R.id.ivCoverDetail)

        tvJudul.text = intent.getStringExtra("judul")
        tvPenulis.text = intent.getStringExtra("penulis")
        tvTahun.text = intent.getStringExtra("tahun")

        Glide.with(this)
            .load(intent.getStringExtra("cover"))
            .into(ivCover)
    }
}
