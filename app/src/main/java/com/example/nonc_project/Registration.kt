package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nonc_project.databinding.ActivityRegistrationBinding

class Registration : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol Registrasi
        binding.button5.setOnClickListener {
            val user = binding.inputUser.text.toString()
            val pass = binding.InputPass.text.toString()
            val conf = binding.InputPassconf.text.toString()

            if (user.isEmpty() || pass.isEmpty() || conf.isEmpty()) {
                Toast.makeText(this, "Input tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != conf) {
                Toast.makeText(this, "Pastikan password sama", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, HomePage::class.java))
            finish()
        }

        // Klik tulisan Login
        binding.Login.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }
    }
}
