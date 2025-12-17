package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.HomePage
import com.example.nonc_project.R
import com.example.nonc_project.databinding.ActivityHasilPrediksiBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class HasilPrediksi : AppCompatActivity() {

    private lateinit var binding: ActivityHasilPrediksiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prediksi = intent.getStringExtra("prediksi") ?: "Tidak diketahui"
        binding.statusBadge.text = prediksi

        // Warna badge
        when (prediksi.uppercase()) {
            "BAIK" -> binding.statusBadge.setBackgroundResource(R.drawable.badge_green)
            "CUKUP" -> binding.statusBadge.setBackgroundResource(R.drawable.badge_blue)
            "KURANG BAIK" -> binding.statusBadge.setBackgroundResource(R.drawable.badge_yellow)
            else -> binding.statusBadge.setBackgroundResource(R.drawable.badge_red)
        }

        // Deskripsi
        binding.resultDescription.text = when (prediksi) {
            "Baik" -> "🔥 Kamu berada pada kategori sangat baik! Pertahankan pola belajar."
            "Cukup" -> "👍 Cukup baik! Masih ada ruang untuk berkembang."
            "Kurang Baik" -> "⚠ Perlu peningkatan belajar dan manajemen waktu."
            else -> "❌ Prediksi gagal atau data tidak valid."
        }

        // Bottom Nav
        binding.bottomNavigation.setOnItemSelectedListener {
            if (it.itemId == R.id.nav_home) {
                navigateHome()
                true
            } else false
        }

        // Tombol simpan + kembali
        binding.btnBackHome.setOnClickListener {
            savePredictionAndGoHome()
        }
    }

    private fun savePredictionAndGoHome() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            navigateHome()
            return
        }

        val uid = user.uid
        val database = FirebaseDatabase.getInstance().reference

        val predictionRef = database
            .child("ml_result")
            .child(uid)
            .push()

        val data = MLInputHolder.data

        val predictionMap = mapOf(
            "result" to binding.statusBadge.text.toString(),
            "timestamp" to System.currentTimeMillis(),
            "inputs" to mapOf(
                "hoursStudied" to data.hoursStudied,
                "attendance" to data.attendance,
                "sleepHours" to data.sleepHours,
                "previousScores" to data.previousScores,
                "motivation" to data.motivation,
                "extracurricular" to data.extracurricular,
                "tutoringSessions" to data.tutoringSessions,
                "physicalActivity" to data.physicalActivity,
                "learningDisabilities" to data.learningDisabilities
            )
        )

        predictionRef.setValue(predictionMap)
            .addOnSuccessListener { navigateHome() }
            .addOnFailureListener { navigateHome() }
    }

    private fun navigateHome() {
        val intent = Intent(this, HomePage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
