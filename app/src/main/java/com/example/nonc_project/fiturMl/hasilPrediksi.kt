package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.HomePage
import com.example.nonc_project.R
import com.example.nonc_project.databinding.ActivityHasilPrediksiBinding

class hasilPrediksi : AppCompatActivity() {

    private lateinit var binding: ActivityHasilPrediksiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔥 Ambil hasil prediksi dari ML
        val prediksi = intent.getStringExtra("prediksi") ?: "Tidak diketahui"

        binding.statusBadge.text = prediksi

        binding.resultDescription.text = when (prediksi) {
            "A" -> "Kamu sangat luar biasa! Pertahankan performamu 👍"
            "AB" -> "Kamu sudah bagus, masih ada ruang untuk lebih baik!"
            "B" -> "Cukup stabil, tetap fokus dan tingkatkan lagi."
            "C" -> "Perlu meningkatkan pola belajar dan konsistensi."
            "F" -> "Perlu perhatian! Mari perbaiki jadwal belajar dan kebiasaanmu."
            else -> "Data tidak valid atau prediksi gagal."
        }

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
