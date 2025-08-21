package com.smktunas.app4_recycleview

import android.os.Bundle
import android.widget.TableRow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smktunas.app4_recycleview.adapter.BukuAdapter
import com.smktunas.app4_recycleview.model.Buku
import com.smktunas.app4_recycleview.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getbuku().enqueue(object : Callback<List<Buku>> {
            override fun onResponse(call: Call<List<Buku>>, response: Response<List<Buku>>) {
                if (response.isSuccessful) {
                    recyclerView.adapter = BukuAdapter(this@MainActivity, response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<Buku>>, t: Throwable){
                Toast.makeText(this@MainActivity,"gagal:${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

}
