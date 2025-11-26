package com.example.nonc_project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        setupRecyclerView()
        setupBottomNavigation()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            CardItem(
                R.drawable.img_hp1,
                "Prediksi Tingkat Sukses Mahasiswa",
                "Dapatkan perkiraan nilai dan kategori performa akademikmu."
            ),
            CardItem(
                R.drawable.img_hp2,
                "Profil Mahasiswa",
                "Menampilkan data diri pengguna seperti nama dan email."
            ),
            CardItem(
                R.drawable.img_hp3,
                "Riwayat Prediksi",
                "Melihat hasil prediksi sebelumnya."
            )
        )

        val adapter = CardAdapter(items) { item, position ->
            Toast.makeText(this, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
    }

    private fun setupBottomNavigation() {
        bottomNav = findViewById(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_history -> {
                    Toast.makeText(this, "History", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}
