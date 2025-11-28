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

        val jamBelajar = intent.getStringExtra("jamBelajar")

        binding.btnSelanjutnya.setOnClickListener {
            val input = binding.inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi kehadiran", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, inputAnalisa3::class.java)
                intent.putExtra("jamBelajar", jamBelajar)
                intent.putExtra("kehadiran", input)
                startActivity(intent)
            }
        }
    }
}