package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa3Binding
import com.example.nonc_project.fiturMl.inputAnalisa4

class inputAnalisa3 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {

            val rawInput = binding.inputField.text.toString().trim().uppercase()

            if (rawInput.isEmpty()) {
                Toast.makeText(this, "Harap isi jawaban (YES/NO)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (rawInput != "YES" && rawInput != "NO") {
                Toast.makeText(this, "Jawaban harus YES atau NO", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 SIMPAN SEBAGAI STRING SESUAI ONNX
            MLInputHolder.data.extracurricularString = rawInput

            // Lanjut ke input ke-4
            startActivity(Intent(this, inputAnalisa4::class.java))
        }
    }
}
