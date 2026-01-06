package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.nonc_project.authentication.LoginPage
import com.example.nonc_project.databinding.ActivityProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGoogleSignOut()
        loadUserFromFirestore()
        setupBottomNavigation()
        setupLogoutButton()
    }

    private fun setupGoogleSignOut() {
        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    // 🔥 SOURCE OF TRUTH = FIRESTORE
    private fun loadUserFromFirestore() {
        val user = auth.currentUser ?: return

        db.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { doc ->
                if (!doc.exists()) return@addOnSuccessListener

                val fullName = doc.getString("fullName") ?: "-"
                val username = doc.getString("username") ?: "-"
                val email = doc.getString("email") ?: "-"

                binding.usernameText.text = username
                binding.namaValue.text = fullName
                binding.emailValue.text = email

                binding.profileImage.setImageResource(R.drawable.img_hp3)
            }
    }

    private fun setupLogoutButton() {
        binding.logoutButton.setOnClickListener {

            auth.signOut()

            googleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this, LoginPage::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.menu_profile

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomePage::class.java))
                    finish()
                    true
                }
                R.id.menu_profile -> true
                else -> false
            }
        }
    }
}
