package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityPrediksiKlasifikasiPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class prediksiKlasifikasiPage : AppCompatActivity() {

    private lateinit var binding: ActivityPrediksiKlasifikasiPageBinding
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrediksiKlasifikasiPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ================= LOAD USERNAME =================
        loadUsername()

        // ================= CARD CLICK =================
        binding.cardAnalisis.setOnClickListener {
            startActivity(Intent(this, inputAnalisa1::class.java))
        }
    }

    private fun loadUsername() {
        val user = auth.currentUser ?: return

        firestore.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    val username = doc.getString("username") ?: "USER"
                    binding.usernameBottom.text = username
                }
            }
    }
}
