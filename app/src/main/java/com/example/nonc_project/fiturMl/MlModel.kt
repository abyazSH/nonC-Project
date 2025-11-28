package com.example.nonc_project.fiturMl

import android.content.Context
import ai.onnxruntime.*
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import org.json.JSONArray

class MlModel(private val context: Context) {

    private lateinit var session: OrtSession
    private val env = OrtEnvironment.getEnvironment()
    private lateinit var inputColumns: List<String>
    private lateinit var labelClasses: Array<String>

    init {
        loadModel()
        loadInputColumns()
        loadLabelClasses()
    }

    private fun loadModel() {
        val modelFile = loadAssetFile("ML.onnx")
        session = env.createSession(modelFile.absolutePath)
    }

    private fun loadInputColumns() {
        val file = loadAssetFile("input_columns.pkl")
        val content = file.readText()
        val jsonArray = JSONArray(content)

        inputColumns = List(jsonArray.length()) { jsonArray.getString(it) }
    }

    private fun loadLabelClasses() {
        val file = loadAssetFile("label_classes.npy")
        val bytes = file.readBytes()

        val text = String(bytes)
        val cleaned = text.replace("[", "").replace("]", "").trim()
        labelClasses = cleaned.split(",").map { it.trim() }.toTypedArray()
    }

    private fun loadAssetFile(name: String): File {
        val file = File(context.cacheDir, name)
        if (!file.exists()) {
            context.assets.open(name).copyTo(FileOutputStream(file))
        }
        return file
    }

    fun predict(input: Mlinput): String {

        val dataMap = mapOf(
            "Hours_Studied" to input.hoursStudied,
            "Attendance" to input.attendance,
            "Extracurricular_Activities" to input.extracurricular,
            "Sleep_Hours" to input.sleepHours,
            "Previous_Scores" to input.previousScores,
            "Motivation_Level" to input.motivationLevel,
            "Tutoring_Sessions" to input.tutoringSessions,
            "Physical_Activity" to input.physicalActivity,
            "Learning_Disabilities" to input.learningDisabilities
        )

        val inputArray = FloatArray(inputColumns.size)

        inputColumns.forEachIndexed { index, colName ->
            inputArray[index] = dataMap[colName] ?: 0f
        }

        val inputTensor = OnnxTensor.createTensor(env, arrayOf(inputArray))

        val result = session.run(mapOf("input" to inputTensor))

        val output = (result[0].value as Array<LongArray>)[0]
        val predictedIndex = output[0].toInt()

        return labelClasses[predictedIndex]
    }
}
