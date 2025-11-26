package com.example.nonc_project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InputAnalisa8Activity : AppCompatActivity() {

    private lateinit var inputField: TextInputEditText
    private lateinit var btnSelanjutnya: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_analisa8)

        inputField = findViewById(R.id.input_field)
        btnSelanjutnya = findViewById(R.id.btn_selanjutnya)

        btnSelanjutnya.setOnClickListener {
            val input = inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi jam olahraga per minggu", Toast.LENGTH_SHORT).show()
            } else {
                val hours = input.toDoubleOrNull()
                if (hours == null || hours < 0) {
                    Toast.makeText(this, "Masukkan jam yang valid", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Jam olahraga: $input jam/minggu", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
