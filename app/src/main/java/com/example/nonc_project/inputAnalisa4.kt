package com.example.nonc_project

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
            val input = binding.inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi jam tidur per hari", Toast.LENGTH_SHORT).show()
            } else {
                val hours = input.toDoubleOrNull()
                if (hours == null || hours < 0 || hours > 24) {
                    Toast.makeText(this, "Masukkan jam tidur valid (0-24)", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, inputAnalisa5::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
