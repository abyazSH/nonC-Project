package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa6Binding
import com.example.nonc_project.fiturMl.inputAnalisa7

class inputAnalisa6 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {

            val input = binding.inputField.text.toString().trim().lowercase()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi tingkat motivasi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (input != "rendah" && input != "sedang" && input != "tinggi") {
                Toast.makeText(this, "Pilihan harus: Rendah, Sedang, atau Tinggi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 SIMPAN STRING ASAP SESUAI ONNX
            MLInputHolder.data.motivationString = input

            startActivity(Intent(this, inputAnalisa7::class.java))
        }
    }
}
