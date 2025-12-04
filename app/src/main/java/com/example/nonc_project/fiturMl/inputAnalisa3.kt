package com.example.nonc_project.fiturMl

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
            handleInput()
        }
    }

    private fun handleInput() {
        val input = binding.inputField.text.toString().trim().uppercase()

        when {
            input.isEmpty() -> showToast("Harap isi jawaban (YES/NO)")

            input != "YES" && input != "NO" -> showToast("Jawaban harus YES atau NO")

            else -> {
                // Simpan sebagai String karena model ONNX butuh string
                MLInputHolder.data = MLInputHolder.data.copy(
                    extracurricular = input // <-- simpan YES/NO
                )

                goNext()
            }
        }
    }

    private fun goNext() {
        startActivity(Intent(this, inputAnalisa4::class.java))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
