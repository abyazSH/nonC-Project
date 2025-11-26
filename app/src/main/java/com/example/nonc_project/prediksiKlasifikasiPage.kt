package com.example.nonc_project

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class HasilPrediksiActivity : AppCompatActivity() {

    private lateinit var cardAnalisis: CardView
    private lateinit var usernameBottom: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_prediksi)

        cardAnalisis = findViewById(R.id.card_analisis)
        usernameBottom = findViewById(R.id.username_bottom)

        // Set username (bisa dari SharedPreferences atau Intent)
        usernameBottom.text = "John Doe"

        cardAnalisis.setOnClickListener {
            Toast.makeText(this, "Mulai Analisis", Toast.LENGTH_SHORT).show()
            // Navigate ke InputAnalisa1Activity
        }
    }
}
