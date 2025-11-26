package com.example.nonc_project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InputAnalisa5Activity : AppCompatActivity() {

    private lateinit var inputField: TextInputEditText
    private lateinit var btnSelanjutnya: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_analisa5)

        inputField = findViewById(R.id.input_field)
        btnSelanjutnya = findViewById(R.id.btn_selanjutnya)

        btnSelanjutnya.setOnClickListener {
            val input = inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi nilai tahun lalu", Toast.LENGTH_SHORT).show()
            } else {
                val score = input.toDoubleOrNull()
                if (score == null || score < 0 || score > 4) {
                    Toast.makeText(this, "Masukkan nilai valid (0-4)", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Nilai tahun lalu: $input", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
