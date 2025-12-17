package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.HomePage
import com.example.nonc_project.R
import com.example.nonc_project.databinding.ActivityHasilPrediksiBinding

class HasilPrediksi : AppCompatActivity() {

    private lateinit var binding: ActivityHasilPrediksiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prediksi = intent.getStringExtra("prediksi") ?: "Tidak diketahui"

        binding.statusBadge.text = prediksi

        // 🔥 Set warna badge berdasarkan hasil prediksi
        when (prediksi.uppercase()) {
            "Baik" -> binding.statusBadge.setBackgroundResource(R.drawable.badge_green)
            "Cukup" -> binding.statusBadge.setBackgroundResource(R.drawable.badge_blue)
            "Kurang Baik" -> binding.statusBadge.setBackgroundResource(R.drawable.badge_yellow)
            else -> binding.statusBadge.setBackgroundResource(R.drawable.badge_red)
        }

        // 🔥 Description (lebih logis dan motivasional)
        binding.resultDescription.text = when (prediksi) {
            "Baik" -> "🔥 Kamu berada pada kategori sangat baik! Pertahankan pola belajar, manajemen waktu, dan konsistensi yang sudah bagus."
            "Cukup" -> "👍 Cukup baik! Kamu berada di jalur yang benar, tapi masih ada ruang untuk berkembang. Tingkatkan rutinitas belajarmu."
            "Kurang Baik" -> "⚠ Kamu memerlukan peningkatan dalam belajar. Cobalah buat jadwal belajar, tidur cukup, dan minta bantuan tutor bila perlu."
            else -> "❌ Prediksi gagal atau data tidak valid."
        }

        // Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, HomePage::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
