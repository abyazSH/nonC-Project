package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa2Binding

class inputAnalisa2 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {

            val input = binding.inputField.text.toString().trim()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi nilai kehadiran terlebih dahulu.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val value = input.toFloatOrNull()
            if (value == null || value < 0f || value > 100f) {
                Toast.makeText(this, "Masukkan angka valid (0–100).", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🧠 Simpan value dengan copy agar field lain tidak hilang
            MLInputHolder.data = MLInputHolder.data.copy(
                attendance = value
            )

            // ➡️ Lanjut ke halaman berikut
            startActivity(Intent(this, inputAnalisa3::class.java))
        }
    }
}
