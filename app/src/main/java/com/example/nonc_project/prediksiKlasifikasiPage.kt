package com.example.nonc_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityPrediksiKlasifikasiPageBinding

class prediksiKlasifikasiPage : AppCompatActivity() {

    private lateinit var binding: ActivityPrediksiKlasifikasiPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrediksiKlasifikasiPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Klik card "Ayo Analisis" untuk mulai input
        binding.cardAnalisis.setOnClickListener {
            val intent = Intent(this, inputAnalisa1::class.java)
            startActivity(intent)
        }
    }
}
