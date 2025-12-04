package com.example.nonc_project.fiturMl

object MLInputHolder {

    data class MlInput(
        val hoursStudied: Float? = null,
        val attendance: Float? = null,
        val sleepHours: Float? = null,
        val previousScores: Float? = null,
        val tutoringSessions: Float? = null,
        val physicalActivity: Float? = null,
        val extracurricular: String? = null,
        val motivation: String? = null,
        val learningDisabilities: String? = null
    )


    var data: MlInput = MlInput()
}
