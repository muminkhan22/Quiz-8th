package com.example.a8thproject

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a8thproject.databinding.ActivityPlayBinding

class playActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayBinding

    val quizlist = listOf<Quiz>(

        Quiz(
            "বাংলাদেশের  রাজধানীর  কোথায়?",
            "ঢাকা",
            "বরিশাল",
            "কুমিল্লা",
            "চট্টগ্রাম",
            "ঢাকা"
        ),

        Quiz(
            "বাংলাদের বিজয়  দিবস কবে?",
            "১৬শে ডিসেম্বর",
            " ১৫ই আগস্ট",
            "২১শে ফেব্রুয়ারী",
            " ৫ই আগস্ট",
            "১৬শে ডিসেম্বর"
        ),

        Quiz(
            "বাংলাদেশ  কয়টি বিভাগ আছে?",
            "১০টি",
            "৭টি",
            "৮টি",
            "৬টি",
            "৮টি"
        ),




        Quiz(
            "কোন চিহ্নটি ই-মেইল ঠিকানায় অবশ্যই থাকবে?",
            "@",
            "#",
            "?",
            "$",
            "@"
        ),



        Quiz(
            "নিচের কোন মেমোরিতে Access Time সবচেয়ে কম?",
            "Registers",
            "SSD",
            "RAM",
            "Cache Memori",
            "Registers"
        ),

        Quiz(
            " কোন মাধ্যমে আলোর পালস ব্যবহৃত হয়?",
            "তামার তার",
            "কো-অক্সিয়াল ক্যাবল",
            "অপটিক্যাল ফাইবার",
            "রুপার তার",
            "অপটিক্যাল ফাইবার"
        ),

        Quiz(
            "নিচের কোনটি একটি প্রতিষ্ঠানের ওয়েব ঠিকানাকে নির্দেশ করে?",
            "http",
            "www",
            "URL",
            "HTML",
            "URL"
        ),


        )
    var index = 0
    var updatQuestion = 1
    var hasFinished = false

    var skip = -1
    var correct = 0
    var wrong = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initQuestion()
        binding.nextQusestion.setOnClickListener {
            shoNextQuestion()

        }

    }

    private fun initQuestion() {

        val quiz = quizlist[index]

        binding.apply {
            textTV.text = quiz.question
            optionBtn1.text = quiz.option1
            optionBtn2.text = quiz.option2
            optionBtn3.text = quiz.option3
            optionBtn4.text = quiz.option4
        }
    }

    private fun shoNextQuestion() {
        checkAnswer()
        binding.apply {

            if (updatQuestion < quizlist.size) {
                updatQuestion++
                initQuestion()
            } else if (index <= quizlist.size - 1) {

                index++
            } else {
                hasFinished = true
            }
            radioGroup.clearCheck()

        }

    }

    private fun checkAnswer() {

        binding.apply {

            if (radioGroup.checkedRadioButtonId == -1) {

                skip++
            } else {
                val checkButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                val chackAnswer = checkButton.text.toString()


                if (chackAnswer == quizlist[index].answer) {
                    correct++
                    showAlertDialoge("Correct Answer")
                } else {
                    wrong++
                    showAlertDialoge("worng Answer")
                }
            }

            if (index <= quizlist.size - 1) {
                index++
            } else {
                showAlertDialoge("Finished")


            }


        }

    }

    fun showAlertDialoge(massage: String) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(massage)

        builder.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {

                if (massage == "Finished") {
                    val intent = Intent(this@playActivity, ResultActivity::class.java)

                    intent.putExtra("skip", skip)
                    intent.putExtra("Correct", correct)
                    intent.putExtra("worng", wrong)


                    startActivity(intent)
                }
            }


        })
        val alertDialog = builder.create()

        alertDialog.show()


     }
}


