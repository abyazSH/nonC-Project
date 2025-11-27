package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa1Binding

class inputAnalisa1 : AppCompatActivity() {  // Sesuai nama file

    private lateinit var binding: ActivityInputAnalisa1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            val input = binding.inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi data", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, inputAnalisa2::class.java)
                startActivity(intent)
            }
        }
    }
}
