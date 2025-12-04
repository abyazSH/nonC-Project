package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa7Binding

class inputAnalisa7 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {

            val raw = binding.inputField.text.toString().trim()

            if (raw.isEmpty()) {
                Toast.makeText(this, "Harap isi jam bimbingan belajar per minggu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hours = raw.toFloatOrNull()
            if (hours == null || hours < 0 || hours > 50) {
                Toast.makeText(this, "Masukkan jam valid (0–50 jam/minggu)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan data dengan format baru immutable
            MLInputHolder.data = MLInputHolder.data.copy(tutoringSessions = hours)

            startActivity(Intent(this, inputAnalisa8::class.java))
        }
    }
}
