package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InputAnalisa2Activity : AppCompatActivity() {

    private lateinit var inputField: TextInputEditText
    private lateinit var btnSelanjutnya: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_analisa2)

        inputField = findViewById(R.id.input_field)
        btnSelanjutnya = findViewById(R.id.btn_selanjutnya)

        btnSelanjutnya.setOnClickListener {
            val input = inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi persentase kehadiran", Toast.LENGTH_SHORT).show()
            } else {
                val percentage = input.toDoubleOrNull()
                if (percentage == null || percentage < 0 || percentage > 100) {
                    Toast.makeText(this, "Masukkan persentase valid (0-100)", Toast.LENGTH_SHORT).show()
                } else {
                    // Simpan data atau kirim ke activity berikutnya
                    Toast.makeText(this, "Kehadiran: $input%", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}
