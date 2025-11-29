package com.example.nonc_project.fiturMl

data class Mlinput(
    var hoursStudied: Float? = null,
    var attendance: Float? = null,
    var extracurricularString: String? = null,      // YES / NO
    var sleepHours: Float? = null,
    var previousScores: Float? = null,
    var motivationString: String? = null,           // rendah / sedang / tinggi
    var tutoringSessions: Float? = null,
    var physicalActivity: Float? = null,
    var learningDisabilitiesString: String? = null  // Ya / Tidak / Kadang-kadang
)

object MLInputHolder {
    val data = Mlinput()
}
