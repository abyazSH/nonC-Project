package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa1Binding
import com.example.nonc_project.fiturMl.inputAnalisa2

class inputAnalisa1 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            val input = binding.inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val value = input.toFloatOrNull()
            if (value == null || value < 0) {
                Toast.makeText(this, "Masukkan angka yang valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 SIMPAN nilai jam belajar
            MLInputHolder.data.hoursStudied = value

            // 🔥 Lanjut ke input berikutnya
            startActivity(Intent(this, inputAnalisa2::class.java))
        }
    }
}
