package com.example.nonc_project.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.HomePage
import com.example.nonc_project.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp

class Registration : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.button5.setOnClickListener {
            registerUser()
        }

        binding.Login.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }
    }

    private fun registerUser() {
        val fullName = binding.inputFullName.text.toString().trim()
        val username = binding.inputUser.text.toString().trim().uppercase()
        val email = binding.inputEmail.text.toString().trim()
        val password = binding.InputPass.text.toString().trim()
        val confirm = binding.InputPassconf.text.toString().trim()

        if (
            fullName.isEmpty() ||
            username.isEmpty() ||
            email.isEmpty() ||
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
            password.length < 6 ||
            password != confirm
        ) {
            Toast.makeText(this, "Data tidak valid", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = auth.currentUser ?: return@addOnSuccessListener

                val userData = hashMapOf(
                    "uid" to user.uid,
                    "fullName" to fullName,
                    "username" to username,
                    "email" to email,
                    "createdAt" to Timestamp.now()
                )

                firestore.collection("users")
                    .document(user.uid)
                    .set(userData)

                // 🔑 SIMPAN LOOKUP USERNAME
                firestore.collection("usernames")
                    .document(username)
                    .set(
                        mapOf(
                            "email" to email,
                            "uid" to user.uid
                        )
                    )

                        startActivity(Intent(this, HomePage::class.java))
                        finish()

            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
    }
}
