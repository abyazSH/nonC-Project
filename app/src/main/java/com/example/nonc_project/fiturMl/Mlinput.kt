package com.example.nonc_project.fiturMl

data class Mlinput(
    var hoursStudied: Float? = null,
    var attendance: Float? = null,
    var extracurricular: Float? = null,
    var sleepHours: Float? = null,
    var previousScores: Float? = null,
    var motivationLevel: Float? = null,
    var tutoringSessions: Float? = null,
    var physicalActivity: Float? = null,
    var learningDisabilities: Float? = null
)

object MLInputHolder {
    val data = Mlinput()
}
