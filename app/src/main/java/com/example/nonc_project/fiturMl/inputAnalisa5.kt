package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa5Binding
import com.example.nonc_project.fiturMl.inputAnalisa6

class inputAnalisa5 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {

            val input = binding.inputField.text.toString().trim()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi nilai tahun lalu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val score = input.toFloatOrNull()
            if (score == null || score < 0 || score > 100) {
                Toast.makeText(this, "Masukkan nilai valid (0-100)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 SIMPAN NILAI KE HOLDER
            MLInputHolder.data.previousScores = score

            // Lanjut ke input berikutnya
            startActivity(Intent(this, inputAnalisa6::class.java))
        }
    }
}
