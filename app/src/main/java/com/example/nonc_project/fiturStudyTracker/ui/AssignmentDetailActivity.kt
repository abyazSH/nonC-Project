    package com.example.nonc_project.fiturStudyTracker.ui

    import android.os.Bundle
    import androidx.activity.viewModels
    import androidx.appcompat.app.AppCompatActivity
    import com.example.nonc_project.databinding.ActivityAssignmentDetailBinding
    import com.example.nonc_project.fiturStudyTracker.viewmodel.AssignmentViewModel

    class AssignmentDetailActivity : AppCompatActivity() {

        private lateinit var binding: ActivityAssignmentDetailBinding
        private val viewModel: AssignmentViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityAssignmentDetailBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val title = intent.getStringExtra("TITLE") ?: return
            val desc = intent.getStringExtra("DESC") ?: ""
            val status = intent.getStringExtra("STATUS") ?: ""
            val due = intent.getLongExtra("DUE", 0L)

            binding.tvTitle.text = title
            binding.tvDesc.text = desc
            binding.tvStatus.text = status
            binding.tvDue.text = "Due: ${java.util.Date(due)}"

            binding.btnDone.setOnClickListener {
                viewModel.markAsDone(title)
                finish()
            }
        }
    }
