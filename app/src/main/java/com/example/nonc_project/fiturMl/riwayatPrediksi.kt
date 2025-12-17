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
import com.google.firebase.database.*

class riwayatPrediksi : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatPrediksiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.usernameHeader.text = "Halo Pengguna"

        setupRecyclerView()
        setupBottomNavigation()
    }

    private fun setupRecyclerView() {
        binding.recyclerRiwayat.layoutManager = LinearLayoutManager(this)

        val user = FirebaseAuth.getInstance().currentUser ?: return
        val uid = user.uid

        val ref = FirebaseDatabase.getInstance()
            .reference
            .child("ml_result")
            .child(uid)

        ref.orderByChild("timestamp")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<RiwayatModel>()

                    for (data in snapshot.children) {
                        val model = RiwayatModel(
                            id = data.key ?: "",
                            result = data.child("result").getValue(String::class.java) ?: "-",
                            timestamp = data.child("timestamp").getValue(Long::class.java) ?: 0L
                        )
                        list.add(model)
                    }

                    list.reverse()

                    binding.recyclerRiwayat.adapter =
                        RiwayatAdapter(list) { item ->
                            Toast.makeText(
                                this@riwayatPrediksi,
                                "Klik: ${item.result}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@riwayatPrediksi,
                        "Gagal memuat data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(
                        Intent(this, HomePage::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
