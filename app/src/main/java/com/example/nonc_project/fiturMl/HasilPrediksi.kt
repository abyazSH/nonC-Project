package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.HomePage
import com.example.nonc_project.R
import com.example.nonc_project.databinding.ActivityHasilPrediksiBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HasilPrediksi : AppCompatActivity() {

    private lateinit var binding: ActivityHasilPrediksiBinding

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prediksi = intent.getStringExtra("prediksi") ?: "Tidak diketahui"
        binding.statusBadge.text = prediksi

        when (prediksi.uppercase()) {
            "BAIK" -> binding.statusBadge.setBackgroundResource(R.drawable.badge_green)
            "CUKUP" -> binding.statusBadge.setBackgroundResource(R.drawable.badge_blue)
            "KURANG BAIK" -> binding.statusBadge.setBackgroundResource(R.drawable.badge_yellow)
            else -> binding.statusBadge.setBackgroundResource(R.drawable.badge_red)
        }

        binding.resultDescription.text = when (prediksi) {
            "Baik" -> "🔥 Kamu berada pada kategori sangat baik!"
            "Cukup" -> "👍 Masih bisa ditingkatkan."
            "Kurang Baik" -> "⚠ Perlu perbaikan pola belajar."
            else -> "❌ Prediksi gagal."
        }

        binding.btnBackHome.setOnClickListener {
            saveToFirestoreAndGoHome()
        }
    }

    private fun saveToFirestoreAndGoHome() {
        val user = auth.currentUser ?: return navigateHome()

        val data = MLInputHolder.data

        val doc = hashMapOf(
            "userId" to user.uid,
            "result" to binding.statusBadge.text.toString(),
            "timestamp" to System.currentTimeMillis(),
            "inputs" to mapOf(
                "hoursStudied" to data.hoursStudied,
                "attendance" to data.attendance,
                "sleepHours" to data.sleepHours,
                "previousScores" to data.previousScores,
                "tutoringSessions" to data.tutoringSessions,
                "physicalActivity" to data.physicalActivity,
                "extracurricular" to data.extracurricular,
                "motivation" to data.motivation,
                "learningDisabilities" to data.learningDisabilities
            )
        )

        db.collection("ml_result")
            .add(doc)
            .addOnSuccessListener { navigateHome() }
            .addOnFailureListener { navigateHome() }
    }

    private fun navigateHome() {
        startActivity(
            Intent(this, HomePage::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
        finish()
    }
}
