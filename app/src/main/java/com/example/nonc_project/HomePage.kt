package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonc_project.databinding.ActivityHomePageBinding
import com.example.nonc_project.fiturMl.prediksiKlasifikasiPage
import com.example.nonc_project.fiturMl.riwayatPrediksi
import com.example.nonc_project.fiturProjectTask.ui.ProjectListActivity

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

            }
        }

        binding.recyclerView.adapter = adapter
    }
}
