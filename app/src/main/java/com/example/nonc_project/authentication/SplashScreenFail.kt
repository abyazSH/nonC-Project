package com.example.nonc_project.authentication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nonc_project.R

class SplashScreenFail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen_fail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val errorMessage = intent.getStringExtra("error_message") ?: "Terjadi kesalahan, silahkan coba lagi."
        findViewById<TextView>(R.id.error_message).text = errorMessage
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        },2500)
    }
}