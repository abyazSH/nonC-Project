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

        // immediately show current holder values (for quick debug)
        logHolderValues("onCreate - before processing")

        Handler(Looper.getMainLooper()).postDelayed({
            performAnalysis()
        }, 600L) // singkat; ganti ke 2000 jika mau simulasi lebih lama
    }

    private fun performAnalysis() {
        // 1) validasi dulu semua input
        val missing = validateInputs()
        if (missing.isNotEmpty()) {
            val message = "Data input tidak lengkap:\n" + missing.joinToString("\n") +
                    "\n\nSilakan kembali dan isi semua input."
            Log.w(TAG, message)
            Toast.makeText(this, "Input tidak lengkap, periksa kembali.", Toast.LENGTH_LONG).show()
            showAlert("Input tidak lengkap", message)
            // kembali ke activity input terakhir supaya user perbaiki
            finish()
            return
        }

        // 2) panggil model di try/catch
        try {
            val input = MLInputHolder.data
            val model = MlModel(this) // bisa melempar exception saat load
            Log.d(TAG, "Memanggil predict() dengan data: $input")
            val hasilPrediksi = model.predict(input)

            // kirim ke hasilPrediksi activity
            val intent = Intent(this, hasilPrediksi::class.java)
            intent.putExtra("prediksi", hasilPrediksi)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            // tangkap semuanya, tampilkan stacktrace agar mudah debug
            Log.e(TAG, "Error saat analisa: ", e)
            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))
            val errText = sw.toString()

            // Tampilkan dialog dengan pesan singkat + tombol "Copy" / "OK"
            showAlert("Terjadi kesalahan saat analisa", e.message ?: "Unknown error\n\n$errText")

            // juga tunjukkan Toast supaya cepat kelihatan di device
            Toast.makeText(this, "Analisa gagal: ${e.message}", Toast.LENGTH_LONG).show()

            // jangan langsung finish() agar user melihat pesan; kalau mau tutup:
            // finish()
        }
    }

    private fun validateInputs(): List<String> {
        val missing = mutableListOf<String>()
        val d = MLInputHolder.data

        if (d.hoursStudied == null) missing.add("Hours_Studied")
        if (d.attendance == null) missing.add("Attendance")
        if (d.extracurricular == null) missing.add("Extracurricular_Activities")
        if (d.sleepHours == null) missing.add("Sleep_Hours")
        if (d.previousScores == null) missing.add("Previous_Scores")
        if (d.motivationLevel == null) missing.add("Motivation_Level")
        if (d.tutoringSessions == null) missing.add("Tutoring_Sessions")
        if (d.physicalActivity == null) missing.add("Physical_Activity")
        if (d.learningDisabilities == null) missing.add("Learning_Disabilities")

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
        Log.d(TAG, "$tagSuffix -> MLInputHolder: hoursStudied=${d.hoursStudied}, attendance=${d.attendance}, " +
                "extracurricular=${d.extracurricular}, sleepHours=${d.sleepHours}, previousScores=${d.previousScores}, " +
                "motivationLevel=${d.motivationLevel}, tutoringSessions=${d.tutoringSessions}, physicalActivity=${d.physicalActivity}, " +
                "learningDisabilities=${d.learningDisabilities}")
    }
}
