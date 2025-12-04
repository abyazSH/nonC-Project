package com.example.nonc_project.fiturMl

import android.app.AlertDialog
import com.example.nonc_project.ml.MlModel
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nonc_project.databinding.ActivityAnalisaLoadingBinding
import java.io.PrintWriter
import java.io.StringWriter

class analisaLoading : AppCompatActivity() {

    private lateinit var binding: ActivityAnalisaLoadingBinding

    companion object {
        private const val TAG = "AnalisaLoading"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalisaLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logValues("onCreate - before processing")

        Handler(Looper.getMainLooper()).postDelayed({
            performAnalysis()
        }, 1200)
    }

    private fun performAnalysis() {

        val missingFields = validateInputs()

        if (missingFields.isNotEmpty()) {

            val message = """
                ❌ Data input tidak lengkap:
                
                ${missingFields.joinToString("\n")}

                Silakan kembali dan lengkapi input yang belum terisi.
            """.trimIndent()

            Log.w(TAG, message)

            showAlert("Input Belum Lengkap", message)

            Toast.makeText(this, "Silakan isi semua input.", Toast.LENGTH_LONG).show()

            finish()
            return
        }

        try {
            val input = MLInputHolder.data
            val model = MlModel(this)

            Log.d(TAG, "Predict() dipanggil dengan data: $input")

            val result = model.predict(input)

            val intent = Intent(this, HasilPrediksi::class.java).apply {
                putExtra("prediksi", result)
            }

            startActivity(intent)
            finish()

        } catch (e: Exception) {

            val writer = StringWriter()
            e.printStackTrace(PrintWriter(writer))

            Log.e(TAG, "Error saat analisa:\n${writer}", e)

            showAlert("Kesalahan Analisa", "Terjadi kesalahan saat memproses prediksi.")

            Toast.makeText(this, "Gagal melakukan analisa.", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateInputs(): List<String> {
        val d = MLInputHolder.data
        return buildList {
            if (d.hoursStudied == null) add("Hours Studied")
            if (d.attendance == null) add("Attendance")
            if (d.sleepHours == null) add("Sleep Hours")
            if (d.previousScores == null) add("Previous Scores")
            if (d.tutoringSessions == null) add("Tutoring Sessions")
            if (d.physicalActivity == null) add("Physical Activity")
            if (d.extracurricular == null) add("Extracurricular Activities")
            if (d.motivation == null) add("Motivation Level")
            if (d.learningDisabilities == null) add("Learning Disabilities")
        }
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun logValues(where: String) {
        val d = MLInputHolder.data
        Log.d(
            TAG, """
            $where
            hoursStudied=${d.hoursStudied},
            attendance=${d.attendance},
            sleepHours=${d.sleepHours},
            prevScores=${d.previousScores},
            motivation=${d.motivation},
            extracurricular=${d.extracurricular},
            learningDisabilities=${d.learningDisabilities},
            tutoringSessions=${d.tutoringSessions},
            physicalActivity=${d.physicalActivity}
        """.trimIndent()
        )
    }
}
