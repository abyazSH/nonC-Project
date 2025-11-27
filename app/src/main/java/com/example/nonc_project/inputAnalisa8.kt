package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa8Binding

class inputAnalisa8 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa8Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            val input = binding.inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi jam olahraga per minggu", Toast.LENGTH_SHORT).show()
            } else {
                val hours = input.toDoubleOrNull()
                if (hours == null || hours < 0) {
                    Toast.makeText(this, "Masukkan jam yang valid", Toast.LENGTH_SHORT).show()
                } else {
                    // Ke AnalisaLoadingActivity
                    val intent = Intent(this, inputAnalisa9::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
