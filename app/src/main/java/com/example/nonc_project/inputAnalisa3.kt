package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa3Binding

class inputAnalisa3 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            val input = binding.inputField.text.toString().trim().uppercase()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi jawaban (YES/NO)", Toast.LENGTH_SHORT).show()
            } else if (input != "YES" && input != "NO") {
                Toast.makeText(this, "Jawaban harus YES atau NO", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, inputAnalisa4::class.java)
                startActivity(intent)
            }
        }
    }
}
