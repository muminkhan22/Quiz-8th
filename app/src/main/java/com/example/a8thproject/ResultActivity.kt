package com.example.a8thproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a8thproject.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val skip = intent.getIntExtra("skip",0)
        val correct =intent.getIntExtra("Correct",0)
        val worng = intent.getIntExtra("worng",0)

        binding.resultTV1.text= "Number of skop : $skip\n"
        binding.resultTV2.text="Number of Correct : $correct\n"
        binding.resultTV3.text="Number of worng : $worng"

    }
}