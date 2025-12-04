package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa6Binding

class inputAnalisa6 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {

            val input = binding.inputField.text.toString().trim().lowercase()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi tingkat motivasi terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val validOptions = listOf("rendah", "sedang", "tinggi")

            if (!validOptions.contains(input)) {
                Toast.makeText(this, "Pilihan tidak valid. Pilih: Rendah, Sedang, atau Tinggi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan input user ke MLInputHolder
            MLInputHolder.data = MLInputHolder.data.copy(motivation = input)

            // Pindah halaman
            startActivity(Intent(this, inputAnalisa7::class.java))
        }
    }
}
