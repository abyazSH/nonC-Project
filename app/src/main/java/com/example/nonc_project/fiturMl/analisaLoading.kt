package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityAnalisaLoadingBinding
import com.example.nonc_project.fiturMl.hasilPrediksi

class analisaLoading : AppCompatActivity() {

    private lateinit var binding: ActivityAnalisaLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalisaLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Simulasi proses analisis selama 5 detik
        Handler(Looper.getMainLooper()).postDelayed({
            // Setelah loading selesai, otomatis ke HasilPrediksi
            val intent = Intent(this, hasilPrediksi::class.java)
            startActivity(intent)
            finish() // Tutup loading activity agar tidak bisa back
        }, 5000) // 5000 ms = 5 detik
    }
}