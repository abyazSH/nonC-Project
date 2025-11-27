package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityRiwayatPrediksiBinding

class riwayatPrediksi : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatPrediksiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set username
        binding.usernameHeader.text = "Halo [username]"

        setupRecyclerView()
        setupBottomNavigation()

        // View All click
        binding.viewAll.setOnClickListener {
            Toast.makeText(this, "View All clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerRiwayat.layoutManager = LinearLayoutManager(this)

        // Data dummy riwayat (6 items)
        val items = listOf(
            itemRiwayat("Tanggal ****", "Lulus"),
            itemRiwayat("Tanggal ****", "Lulus"),
            itemRiwayat("Tanggal ****", "Lulus"),
            itemRiwayat("Tanggal ****", "Lulus"),
            itemRiwayat("Tanggal ****", "Lulus"),
            itemRiwayat("Tanggal ****", "Lulus")
        )

        val adapter = RiwayatAdapter(items) { item ->
            // Klik item untuk lihat detail
            Toast.makeText(this, "Clicked: ${item.tanggal}", Toast.LENGTH_SHORT).show()
            // Bisa navigate ke detail hasil prediksi
            // val intent = Intent(this, hasilPrediksi::class.java)
            // startActivity(intent)
        }

        binding.recyclerRiwayat.adapter = adapter
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, HomePage::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}
