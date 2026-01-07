package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.HomePage
import com.example.nonc_project.R
import com.example.nonc_project.databinding.ActivityRiwayatPrediksiBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class riwayatPrediksi : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatPrediksiBinding

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        loadData()
        setupBottomNavigation()
    }

    private fun setupRecycler() {
        binding.recyclerRiwayat.layoutManager = LinearLayoutManager(this)
    }

    private fun loadData() {
        val user = auth.currentUser
        if (user == null) {
            Toast.makeText(this, "User belum login", Toast.LENGTH_SHORT).show()
            return
        }

        db.collection("ml_result")
            .whereEqualTo("userId", user.uid)
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    Toast.makeText(this, "Riwayat kosong", Toast.LENGTH_SHORT).show()
                    return@addOnSuccessListener
                }

                val list = snapshot.documents.map {
                    RiwayatModel(
                        id = it.id,
                        result = it.getString("result") ?: "-",
                        timestamp = it.getLong("timestamp") ?: 0L
                    )
                }.sortedByDescending { it.timestamp }

                binding.recyclerRiwayat.adapter =
                    RiwayatAdapter(list) {}
            }
            .addOnFailureListener {
                Toast.makeText(this, "Gagal memuat riwayat", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            if (it.itemId == R.id.menu_home) {
                startActivity(Intent(this, HomePage::class.java))
                finish()
                true
            } else false
        }
    }
}
