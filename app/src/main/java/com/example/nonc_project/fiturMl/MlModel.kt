package com.example.nonc_project.fiturMl

import android.content.Context
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtSession
import java.io.File
import java.io.FileOutputStream

class MlModel(private val context: Context) {

    private val env: OrtEnvironment = OrtEnvironment.getEnvironment()
    private val session: OrtSession

    // mapping index -> label (pastikan urutan sama seperti saat training)
    private val labels = arrayOf("A", "AB", "B", "C", "F")

    init {
        val modelFile = copyAssetToCache("student_success.onnx")
        session = env.createSession(modelFile.absolutePath)
    }

    private fun copyAssetToCache(name: String): File {
        val out = File(context.cacheDir, name)
        if (!out.exists()) {
            context.assets.open(name).use { input ->
                FileOutputStream(out).use { output ->
                    input.copyTo(output)
                }
            }
        }
        return out
    }

    /**
     * Predict: returns label string (e.g. "B").
     * Safe: handles nulls by using defaults and closes tensors/results to avoid native leaks.
     */
    fun predict(input: Mlinput): String {
        // create map of OnnxTensor
        val tensorMap = mutableMapOf<String, OnnxTensor>()

        try {
            // --- STRING inputs (use defaults if null) ---
            val extracur = input.extracurricularString ?: "NO"
            tensorMap["Extracurricular_Activities"] = OnnxTensor.createTensor(env, arrayOf(extracur))

            val motiv = input.motivationString ?: "rendah"
            tensorMap["Motivation_Level"] = OnnxTensor.createTensor(env, arrayOf(motiv))

            val learning = input.learningDisabilitiesString ?: "Tidak"
            tensorMap["Learning_Disabilities"] = OnnxTensor.createTensor(env, arrayOf(learning))

            // --- FLOAT inputs (shape [1]) ---
            tensorMap["Hours_Studied"] = OnnxTensor.createTensor(env, floatArrayOf(input.hoursStudied ?: 0f))
            tensorMap["Attendance"] = OnnxTensor.createTensor(env, floatArrayOf(input.attendance ?: 0f))
            tensorMap["Sleep_Hours"] = OnnxTensor.createTensor(env, floatArrayOf(input.sleepHours ?: 0f))
            tensorMap["Previous_Scores"] = OnnxTensor.createTensor(env, floatArrayOf(input.previousScores ?: 0f))
            tensorMap["Tutoring_Sessions"] = OnnxTensor.createTensor(env, floatArrayOf(input.tutoringSessions ?: 0f))
            tensorMap["Physical_Activity"] = OnnxTensor.createTensor(env, floatArrayOf(input.physicalActivity ?: 0f))

            // run model (OrtSession.Result is AutoCloseable)
            session.run(tensorMap).use { results ->
                // outputs[0] is output_label (int64) [None]
                val out0 = results[0].value

                // handle multiple possible shapes returned by onnxruntime java binding
                val predictedIndex: Int = when (out0) {
                    is LongArray -> out0[0].toInt()
                    is Array<*> -> {
                        // could be Array<Long> or Array<LongArray>
                        val first = out0[0]
                        when (first) {
                            is Long -> (first as Long).toInt()
                            is LongArray -> (first as LongArray)[0].toInt()
                            else -> {
                                // fallback: try to parse toInt
                                first.toString().toDoubleOrNull()?.toInt() ?: -1
                            }
                        }
                    }
                    is Number -> out0.toInt()
                    else -> {
                        // fallback
                        try {
                            out0.toString().toDouble().toInt()
                        } catch (e: Exception) {
                            -1
                        }
                    }
                }

                // map to label text
                return if (predictedIndex in labels.indices) labels[predictedIndex] else "Unknown"
            }
        } finally {
            // always close created tensors to prevent native memory leak
            tensorMap.values.forEach { try { it.close() } catch (_: Exception) {} }
        }
    }
}
