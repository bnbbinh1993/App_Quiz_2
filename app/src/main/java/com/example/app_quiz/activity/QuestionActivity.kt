package com.example.app_quiz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_quiz.R
import com.example.app_quiz.adapters.OptionAdapter
import com.example.app_quiz.models.Question
import com.example.app_quiz.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    var quizzes: MutableList<Quiz>? =null
    var questions :MutableMap <String,Question>? =null
    var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        setUpFirestore()
        setUpEventListener()
        // k commit đc

    }

    private fun setUpEventListener() {
        btnPrevious.setOnClickListener{
            index--
            bindViews()
        }
        btnNext.setOnClickListener {
            index++
            bindViews()
        }
        btnSubmit.setOnClickListener {
            Log.d("Final quiz",questions.toString())
        }
    }

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
//        val date = intent.getStringExtra("DATE")
        //if(date != null){
            firestore.collection("quizzes").whereEqualTo("title","basic question")
                .get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty){
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()

                    }
                }
        // chỗ này là để lấy dữ liệu à -- ừ t lên gg
       // } // lỗi này nữa này
        //Nhận tất cả tài liệu trong một bộ sưu tập su dung dieu kien where
       firestore.collection("quizzes")
           .whereEqualTo("title","average questions")
           .get()
           .addOnSuccessListener {
               if(it != null && !it.isEmpty){
                   quizzes = it.toObjects(Quiz::class.java)
                   questions = quizzes!![0].questions

                   bindViews()

               }
           }

    }

    private fun bindViews() {
        btnPrevious.visibility = View.GONE
        btnNext.visibility = View.GONE
        btnSubmit.visibility = View.GONE
        if(index == 1){ // first question
            btnNext.visibility= View.VISIBLE
        }
        else if(index == questions!!.size){ //lastquestion
            btnSubmit.visibility= View.VISIBLE
            btnPrevious.visibility= View.VISIBLE
        }
        else{
            btnNext.visibility= View.VISIBLE
            btnPrevious.visibility= View.VISIBLE
        }
        val question = questions!!["question$index"]
        question?.let{
            description.text = question.description
            val optionAdapter = OptionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)

        }

    }

}