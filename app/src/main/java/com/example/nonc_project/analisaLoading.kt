package com.example.nonc_project

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SedangAnalisaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analisa_loading)

        // Simulasi proses analisis selama 5 detik
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "Analisis selesai!", Toast.LENGTH_SHORT).show()
            // Navigate ke hasil prediksi
            // val intent = Intent(this, HasilPrediksiActivity::class.java)
            // startActivity(intent)
            // finish()
        }, 5000)
    }
}
