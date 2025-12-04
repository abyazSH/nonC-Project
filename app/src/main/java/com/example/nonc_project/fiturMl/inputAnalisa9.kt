package com.example.nonc_project.fiturMl

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityInputAnalisa9Binding

class inputAnalisa9 : AppCompatActivity() {

    private lateinit var binding: ActivityInputAnalisa9Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputAnalisa9Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdown()

        binding.btnAnalisa.setOnClickListener {
            val input = binding.inputField.text.toString().trim()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap pilih jawaban", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val validOps = listOf("Ya", "Tidak", "Kadang-kadang")
            if (!validOps.contains(input)) {
                Toast.makeText(this, "Pilihan tidak valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✔️ Simpan jawaban dengan immutable copy
            MLInputHolder.data = MLInputHolder.data.copy(learningDisabilities = input)

            startActivity(Intent(this, analisaLoading::class.java))
            finish()
        }
    }

    private fun setupDropdown() {
        val options = arrayOf("Ya", "Tidak", "Kadang-kadang")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, options)

        (binding.inputField as? AutoCompleteTextView)?.apply {
            setAdapter(adapter)
            setOnClickListener { showDropDown() }
        }
    }
}
