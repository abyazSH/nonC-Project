package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa7Binding
import com.example.nonc_project.fiturMl.inputAnalisa8

class inputAnalisa7 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            val input = binding.inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi jam bimbingan belajar per minggu", Toast.LENGTH_SHORT).show()
            } else {
                val hours = input.toDoubleOrNull()
                if (hours == null || hours < 0) {
                    Toast.makeText(this, "Masukkan jam yang valid", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, inputAnalisa8::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}