package com.example.nonc_project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InputAnalisa6Activity : AppCompatActivity() {

    private lateinit var inputField: TextInputEditText
    private lateinit var btnSelanjutnya: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_analisa6)

        inputField = findViewById(R.id.input_field)
        btnSelanjutnya = findViewById(R.id.btn_selanjutnya)

        btnSelanjutnya.setOnClickListener {
            val input = inputField.text.toString().trim().lowercase()

            if (input.isEmpty()) {
                Toast.makeText(this, "Harap isi tingkat motivasi", Toast.LENGTH_SHORT).show()
            } else if (input != "rendah" && input != "sedang" && input != "tinggi") {
                Toast.makeText(this, "Pilihan harus: Rendah, Sedang, atau Tinggi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tingkat motivasi: ${input.capitalize()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
