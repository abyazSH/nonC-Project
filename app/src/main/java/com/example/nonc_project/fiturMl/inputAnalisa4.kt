package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa4Binding

class inputAnalisa4 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            handleInput()
        }
    }

    private fun handleInput() {
        val input = binding.inputField.text.toString().trim()

        when {
            input.isEmpty() -> {
                showToast("Harap isi jam tidur per hari")
            }

            input.toFloatOrNull() == null -> {
                showToast("Masukkan angka yang valid")
            }

            input.toFloat() < 0 || input.toFloat() > 24 -> {
                showToast("Masukkan jam tidur valid (0–24)")
            }

            else -> {
                // Simpan menggunakan copy agar data sebelumnya tidak hilang
                MLInputHolder.data = MLInputHolder.data.copy(
                    sleepHours = input.toFloat()
                )

                goNext()
            }
        }
    }

    private fun goNext() {
        startActivity(Intent(this, inputAnalisa5::class.java))
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
