package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InputAnalisa3Activity : AppCompatActivity() {

    private lateinit var inputField: TextInputEditText
    private lateinit var btnSelanjutnya: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_analisa3)

        inputField = findViewById(R.id.input_field)
        btnSelanjutnya = findViewById(R.id.btn_selanjutnya)

        btnSelanjutnya.setOnClickListener {
            val input = inputField.text.toString().trim().uppercase()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi jawaban (YES/NO)", Toast.LENGTH_SHORT).show()
            } else if (input != "YES" && input != "NO") {
                Toast.makeText(this, "Jawaban harus YES atau NO", Toast.LENGTH_SHORT).show()
            } else {
                // Simpan data atau kirim ke activity berikutnya
                Toast.makeText(this, "Aktif UKM: $input", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
