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

class profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGoogleSignOut()
        loadUserData()
        setupBottomNavigation()
        setupLogoutButton()
    }

    private fun setupGoogleSignOut() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun loadUserData() {
        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (account != null) {
            binding.usernameText.text = account.displayName
            binding.namaValue.text = account.givenName ?: account.displayName
            binding.emailValue.text = account.email

            Glide.with(this)
                .load(account.photoUrl)
                .placeholder(R.drawable.img_hp3)
                .into(binding.profileImage)
        }
    }

    private fun setupLogoutButton() {
        binding.logoutButton.setOnClickListener {
            googleSignInClient.signOut().addOnCompleteListener {

                val intent = Intent(this, LoginPage::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }


    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, HomePage::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.menu_profile -> true
                else -> false
            }
        }
    }
}
