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

        // Set hasil prediksi (nanti bisa dari data yang dikirim dari loading)
        binding.statusBadge.text = "LULUS"
        binding.resultDescription.text = """
            Selamat! Hasil prediksi menunjukkan performa akademikmu berada pada tingkat yang baik. 
            Pertahankan kebiasaan belajarmu dengan jadwal yang teratur, usahakan tetap tidur cukup antara 
            6-8 jam setiap malam, dan luangkan waktu untuk berolahraga ringan agar tubuh tetap bugar. Terus 
            tingkatkan motivasi belajar dengan menetapkan target mingguan, aktif berdiskusi di kelas, serta 
            jaga keseimbangan antara waktu belajar dan istirahat. Pola hidup yang sehat dan disiplin akan 
            membantu mempertahankan bahkan meningkatkan prestasimu di masa depan.
        """.trimIndent()

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
                R.id.nav_profile -> {
                    // Navigate ke profile page
                    true
                }
                else -> false
            }
        }
    }
}