package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nonc_project.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Logika Login nanti kita buat
        binding.button4.setOnClickListener {
            val username = binding.inputanUsername.text.toString()
            val password = binding.InputanPassword.text.toString()

            // kondisi ini untuk awal saja yakni jika kosong
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Inputan tidak boleh kosong, silahkan isi inputan yang benar", Toast.LENGTH_SHORT).show()
                val intent=Intent(this, SplashScreenFail::class.java)
                intent.putExtra("error_message", "Inputan tidak boleh kosong, silahkan isi inputan yang benar")
                startActivity(intent)
            }
            else {
                // Jika terisi → login sukses
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()

                // Pindah ke HomePage
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            }
        }
        binding.Registrasi.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }
}