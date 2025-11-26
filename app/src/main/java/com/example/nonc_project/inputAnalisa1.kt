package com.example.nonc_project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InputAnalisaActivity : AppCompatActivity() {

    private lateinit var inputField: TextInputEditText
    private lateinit var btnSelanjutnya: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_analisa1)

        inputField = findViewById(R.id.input_field)
        btnSelanjutnya = findViewById(R.id.btn_selanjutnya)

        btnSelanjutnya.setOnClickListener {
            val input = inputField.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi jam belajar per minggu", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Input: $input jam/minggu", Toast.LENGTH_SHORT).show()
                // Lanjutkan ke activity berikutnya atau proses data
            }
        }
    }
}
