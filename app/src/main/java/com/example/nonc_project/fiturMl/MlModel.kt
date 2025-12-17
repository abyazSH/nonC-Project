package com.example.nonc_project.ml

import android.content.Context
import android.util.Log
import ai.onnxruntime.*
import com.example.nonc_project.fiturMl.MLInputHolder
import java.io.File
import java.io.FileOutputStream

class MlModel(private val context: Context) {

    private val env: OrtEnvironment = OrtEnvironment.getEnvironment()
    private val session: OrtSession

    private val labelByIndex = arrayOf("Kurang baik", "Cukup", "Baik")

    private val codeByLabel = mapOf(
        "Kurang baik" to "Baik",
        "Cukup" to "Cukup",
        "Baik" to "Kurang Baik"
    )

    init {
        val modelFile = copyAssetToCache("student_svc_model.onnx")
        session = env.createSession(modelFile.absolutePath)
    }

    private fun copyAssetToCache(assetName: String): File {
        val file = File(context.cacheDir, assetName)
        if (!file.exists()) {
            context.assets.open(assetName).use { input ->
                FileOutputStream(file).use { output -> input.copyTo(output) }
            }
        }
        return file
    }

    fun predict(input: MLInputHolder.MlInput): String {
        val tensors = mutableMapOf<String, OnnxTensor>()

        try {

            // ================== Normalisasi String ==================
            val extracurricularForModel = when (input.extracurricular?.lowercase()?.trim()) {
                "ya", "yes", "y" -> "YES"
                "tidak", "no", "n" -> "NO"
                else -> "NO"
            }

            val motivationForModel = when (input.motivation?.lowercase()?.trim()) {
                "tinggi", "high" -> "tinggi"
                "rendah", "low" -> "rendah"
                else -> "sedang"
            }

            val learningForModel = when (input.learningDisabilities?.lowercase()?.trim()) {
                "ya", "yes", "y" -> "Ya"
                "kadang", "sometimes" -> "Kadang-kadang"
                else -> "Tidak"
            }

            // ================== Numeric Inputs (2D) ==================
            tensors["Hours_Studied"] = OnnxTensor.createTensor(env, arrayOf(floatArrayOf(input.hoursStudied ?: 0f)))
            tensors["Attendance"] = OnnxTensor.createTensor(env, arrayOf(floatArrayOf(input.attendance ?: 0f)))
            tensors["Sleep_Hours"] = OnnxTensor.createTensor(env, arrayOf(floatArrayOf(input.sleepHours ?: 0f)))
            tensors["Previous_Scores"] = OnnxTensor.createTensor(env, arrayOf(floatArrayOf(input.previousScores ?: 0f)))
            tensors["Tutoring_Sessions"] = OnnxTensor.createTensor(env, arrayOf(floatArrayOf(input.tutoringSessions ?: 0f)))
            tensors["Physical_Activity"] = OnnxTensor.createTensor(env, arrayOf(arrayOf(input.physicalActivity.toString())))

            // ================== String Inputs (2D) ==================
            tensors["Extracurricular_Activities"] =
                OnnxTensor.createTensor(env, arrayOf(arrayOf(extracurricularForModel)))

            tensors["Motivation_Level"] =
                OnnxTensor.createTensor(env, arrayOf(arrayOf(motivationForModel)))

            tensors["Learning_Disabilities"] =
                OnnxTensor.createTensor(env, arrayOf(arrayOf(learningForModel)))


            // ================== Run Model ==================
            session.run(tensors).use { result ->

                val output = result[0].value

                return when (output) {

                    is LongArray -> {
                        val idx = output.firstOrNull()?.toInt() ?: -1
                        val label = labelByIndex.getOrNull(idx) ?: return "?"
                        codeByLabel[label] ?: label
                    }

                    is Array<*> -> {
                        val first = output.firstOrNull()
                        if (first is String) codeByLabel[first] ?: first else "?"
                    }

                    else -> output.toString()
                }
            }

        } catch (e: Exception) {
            Log.e("ONNX_ERROR", "Prediction Failed → ${e.message}")
            return "?"
        } finally {
            tensors.values.forEach { it.close() }
        }
    }
}
