package com.example.nonc_project.fiturMl

import android.app.AlertDialog
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

        logHolderValues("onCreate - before processing")

        Handler(Looper.getMainLooper()).postDelayed({
            performAnalysis()
        }, 600L)
    }

    private fun performAnalysis() {

        val missing = validateInputs()
        if (missing.isNotEmpty()) {
            val message = "Data input tidak lengkap:\n" +
                    missing.joinToString("\n") +
                    "\n\nSilakan kembali dan isi semua input."
            Log.w(TAG, message)
            Toast.makeText(this, "Input tidak lengkap, periksa kembali.", Toast.LENGTH_LONG).show()
            showAlert("Input tidak lengkap", message)
            finish()
            return
        }

        try {
            val input = MLInputHolder.data
            val model = MlModel(this)

            Log.d(TAG, "Memanggil predict() dengan data: $input")

            // 🔥 hasil prediksi dari model (String)
            val hasil = model.predict(input)

            // 🔥 buka Activity HasilPrediksi
            val intent = Intent(this, HasilPrediksi::class.java)
            intent.putExtra("prediksi", hasil)
            startActivity(intent)
            finish()

        } catch (e: Exception) {
            Log.e(TAG, "Error saat analisa: ", e)
            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))

            showAlert("Terjadi kesalahan saat analisa", e.message ?: "Unknown error")
            Toast.makeText(this, "Analisa gagal: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateInputs(): List<String> {
        val missing = mutableListOf<String>()
        val d = MLInputHolder.data

        if (d.hoursStudied == null) missing.add("Hours_Studied")
        if (d.attendance == null) missing.add("Attendance")
        if (d.sleepHours == null) missing.add("Sleep_Hours")
        if (d.previousScores == null) missing.add("Previous_Scores")
        if (d.tutoringSessions == null) missing.add("Tutoring_Sessions")
        if (d.physicalActivity == null) missing.add("Physical_Activity")

        // === FIELD STRING BARU ===
        if (d.extracurricularString == null) missing.add("Extracurricular_Activities (YES/NO)")
        if (d.motivationString == null) missing.add("Motivation_Level (rendah/sedang/tinggi)")
        if (d.learningDisabilitiesString == null) missing.add("Learning_Disabilities (Ya/Tidak/Kadang)")

        return missing
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun logHolderValues(tagSuffix: String) {
        val d = MLInputHolder.data
        Log.d(
            TAG,
            "$tagSuffix -> MLInputHolder:" +
                    " hoursStudied=${d.hoursStudied}," +
                    " attendance=${d.attendance}," +
                    " sleepHours=${d.sleepHours}," +
                    " prevScores=${d.previousScores}," +
                    " motivationString=${d.motivationString}," +
                    " extracurricularString=${d.extracurricularString}," +
                    " learningDisabilitiesString=${d.learningDisabilitiesString}," +
                    " tutoringSessions=${d.tutoringSessions}," +
                    " physicalActivity=${d.physicalActivity}"
        )
    }
}
