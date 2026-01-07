package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityHomePageBinding
import com.example.nonc_project.fiturMl.prediksiKlasifikasiPage
import com.example.nonc_project.fiturMl.riwayatPrediksi
import com.example.nonc_project.fiturProjectTask.ui.ProjectListActivity
import com.example.nonc_project.fiturStudyTracker.ui.StudyTrackerActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load username dari Firestore
        loadUserFromFirestore()

        setupRecyclerView()
        setupBottomNavigation()
    }

    // 🔥 Load username dari Firestore
    private fun loadUserFromFirestore() {
        val user = auth.currentUser

        if (user == null) {
            // Jika user belum login, tampilkan default
            binding.usernameText.text = "Guest"
            return
        }

        // Animasi fade in saat loading
        binding.usernameText.alpha = 0f
        binding.usernameText.text = "Loading..."

        db.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    val username = doc.getString("username") ?: "User"

                    // Set username dengan animasi
                    binding.usernameText.text = username
                    binding.usernameText.animate()
                        .alpha(1f)
                        .setDuration(500)
                        .start()
                } else {
                    // Fallback ke email jika data Firestore tidak ada
                    binding.usernameText.text = user.email?.substringBefore("@") ?: "User"
                    binding.usernameText.alpha = 1f
                }
            }
            .addOnFailureListener {
                // Jika gagal load, tampilkan email
                binding.usernameText.text = user.email?.substringBefore("@") ?: "User"
                binding.usernameText.alpha = 1f
            }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            CardItem(
                R.drawable.img_hp1,
                "Prediksi Tingkat Sukses Mahasiswa",
                "Dapatkan perkiraan performa akademik berdasarkan kebiasaan belajar."
            ),
            CardItem(
                R.drawable.img_hp2,
                "Profil Mahasiswa",
                "Menampilkan data diri mahasiswa."
            ),
            CardItem(
                R.drawable.img_hp3,
                "Riwayat Prediksi",
                "Cek hasil prediksi sebelumnya."
            ),
            CardItem(
                R.drawable.img_hp4,
                "Jejak Project ",
                "Tracking assignment & to-do list."
            ),
            CardItem(
                R.drawable.img_hp5,
                "Studi Tracker",
                "Tracking assignment & to-do list."
            )
        )

        val adapter = CardAdapter(items) { _, position ->
            when (position) {
                0 -> startActivity(Intent(this, prediksiKlasifikasiPage::class.java))
                1 -> startActivity(Intent(this, profile::class.java))
                2 -> startActivity(Intent(this, riwayatPrediksi::class.java))
                3 -> startActivity(Intent(this, ProjectListActivity::class.java))
                4 -> startActivity(Intent(this, StudyTrackerActivity::class.java))
            }
        }
        binding.recyclerView.adapter = adapter
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.menu_home

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> true

                R.id.menu_project -> {
                    startActivity(Intent(this, ProjectListActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.menu_study -> {
                    startActivity(Intent(this, StudyTrackerActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                R.id.menu_profile -> {
                    startActivity(Intent(this, profile::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                else -> false
            }
        }
    }
}
