package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityHomePageBinding
import com.example.nonc_project.fiturMl.prediksiKlasifikasiPage
import com.example.nonc_project.fiturMl.riwayatPrediksi

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            CardItem(
                R.drawable.img_hp1,
                "Prediksi Tingkat Sukses Mahasiswa",
                "Dapatkan perkiraan nilai dan kategori performa akademikmu berdasarkan kebiasaan belajar, motivasi, dan faktor pendukung lainnya."
            ),
            CardItem(
                R.drawable.img_hp2,
                "Profil Mahasiswa",
                "Menampilkan data diri pengguna seperti nama, email, dan program studi yang bisa diedit sesuai kebutuhan."
            ),
            CardItem(
                R.drawable.img_hp3,
                "Riwayat Prediksi",
                "Melihat hasil prediksi sebelumnya untuk memantau perkembangan performa belajar dari waktu ke waktu."
            ),
            CardItem(
                R.drawable.img_hp4,
                "Studi Tracker",
                "Halaman ini adalah tracking record semua assigment dari classrom anda, To do list dan rancangan project anda agar memudahkan dalam timeline dan reminder pengerjaan anda."
            )
        )

        // Lambda onClick dengan position
        val adapter = CardAdapter(items) { item, position ->
            when (position) {
                0 -> {
                    // Item pertama -> Pindah ke prediksiKlasifikasiPage
                    val intent = Intent(this, prediksiKlasifikasiPage::class.java)
                    startActivity(intent)
                }
                1 -> {
                    // Navigate ke Profile Mahasiswa
                    val intent = Intent(this, profile::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this, riwayatPrediksi::class.java)
                    startActivity(intent)
                }
                3 -> {
                    // Item keempat -> Riwayat (bisa ditambahkan nanti)
                    Toast.makeText(this, "Studi Tracker", Toast.LENGTH_SHORT).show()
                }

            }
        }

        binding.recyclerView.adapter = adapter
    }
}
