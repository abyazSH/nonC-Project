package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa2Binding
import com.example.nonc_project.fiturMl.inputAnalisa3

class inputAnalisa2 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            val input = binding.inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi kehadiran", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val attendanceValue = input.toFloatOrNull()
            if (attendanceValue == null || attendanceValue < 0f || attendanceValue > 100f) {
                Toast.makeText(this, "Masukkan angka valid (0–100)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 SIMPAN NILAI KE MLInputHolder
            MLInputHolder.data.attendance = attendanceValue

            // Lanjut ke input berikutnya
            startActivity(Intent(this, inputAnalisa3::class.java))
        }
    }
}
