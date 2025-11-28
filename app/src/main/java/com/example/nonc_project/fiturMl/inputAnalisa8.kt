package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa8Binding
import com.example.nonc_project.fiturMl.inputAnalisa9

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
                return@setOnClickListener
            }

            val hours = input.toFloatOrNull()
            if (hours == null || hours < 0f) {
                Toast.makeText(this, "Masukkan jam yang valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 SIMPAN NILAI KE HOLDER
            MLInputHolder.data.physicalActivity = hours

            // Lanjutkan ke input ke-9
            startActivity(Intent(this, inputAnalisa9::class.java))
        }
    }
}
