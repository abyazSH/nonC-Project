package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa5Binding

class inputAnalisa5 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            handleInput()
        }
    }

    private fun handleInput() {
        val input = binding.inputField.text.toString().trim()

        when {
            input.isEmpty() -> {
                showToast("Harap isi nilai tahun lalu")
            }

            input.toFloatOrNull() == null -> {
                showToast("Masukkan angka yang valid")
            }

            input.toFloat() < 0 || input.toFloat() > 100 -> {
                showToast("Masukkan nilai valid (0–100)")
            }

            else -> {
                // Simpan data menggunakan copy agar variabel lain tidak ter-reset
                MLInputHolder.data = MLInputHolder.data.copy(
                    previousScores = input.toFloat()
                )

                goNext()
            }
        }
    }

    private fun goNext() {
        startActivity(Intent(this, inputAnalisa6::class.java))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
