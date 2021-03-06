package com.example.app_quiz.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.app_quiz.R
import com.example.app_quiz.adapters.QuizAdapter
import com.example.app_quiz.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.*;

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var fireStore: FirebaseFirestore
    //àm nhiệm vụ điều khiển việc đóng mở DrawerLayout
    // cho chúng ta.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populateDummyData()
        setUpView()

    }

    private fun populateDummyData() {
        quizList.add(Quiz("12", "12-1-2021"))
        quizList.add(Quiz("13", "13-1-2021"))
        quizList.add(Quiz("14", "14-1-2021"))
        quizList.add(Quiz("15", "15-1-2021"))
        quizList.add(Quiz("16", "16-1-2021"))
        quizList.add(Quiz("17", "17-1-2021"))
        quizList.add(Quiz("12", "12-1-2021"))
        quizList.add(Quiz("13", "13-1-2021"))
        quizList.add(Quiz("14", "14-1-2021"))
        quizList.add(Quiz("15", "15-1-2021"))
        quizList.add(Quiz("16", "16-1-2021"))
        quizList.add(Quiz("17", "17-1-2021"))


    }

    private fun setUpView() {
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecycleView()
        //setUpDatePicker()

    }

    private fun setUpDatePicker() {
        bntDatePicker.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build() // bạn có thể thiết lập bộ chọn ngày của mình .
            datePicker.show(supportFragmentManager, "DatePicker")
            //The supplied listener is called when the user confirms a valid selection.
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)
                val dateFormatter = SimpleDateFormat("dd-mm-yyyy")
                val date = dateFormatter.format(Date(it))

                val intent = Intent(this, QuestionActivity::class.java)
//                intent.putExtra("DATE",date)
                startActivity(intent)
            }

            //The supplied listener is called when the user clicks the cancel button
            datePicker.addOnNegativeButtonClickListener{
                Log.d("DATEPICKER", datePicker.headerText)

            }

            //The supplied listener is called when the user cancels the picker via back button or a touch outside the view.

            datePicker.addOnCancelListener{
                Log.d("DATEPICKER", "Date picker Cancelled")
            }
        }
    }

    private fun setUpFireStore() {

//        val firebaseRespond =FirebaseRespond()
//        if(firebaseRespond.getUser()==null){
//            firebaseRespond.createUser().addOnCompleteListener{
//                if(it.isSuccessful){
//                    loadPostData()
//                }
//                else{
//
//                }
//            }
//        }
//        //used logged in
//        else{
//           loadPostData()
//        }
//        val firebaseAuth : FirebaseAuth= FirebaseAuth.getInstance()
//        val rootRef = FirebaseFirestore.getInstance()
       // val applicationsRef = rootRef.collection("quizz")
      //  val applicationIdRef = applicationsRef.document()
        //applicationIdRef.get().addOnCompleteListener { task: Task<DocumentSnapshot?> ->
          //  if (task.isSuccessful) {
            //    val document = task.result
              //  if (document!!.exists()) {
                //    val users = document!!["aa"] as List<Map<String, Any>>?
             //   }
           // }
        //}

//        val docRef = FirebaseFirestore.getInstance().collection("quiz").document("aa")
//        docRef.get()
//                .addOnSuccessListener { document ->
//                    Log.d("check","check document ${document.data}")
//                    Log.d("DATA", document?.toObject(Quiz::class.java).toString())
//                    quizList.clear()
//
//                    for (document : document.toObject(Quiz::class.java))
//                    adapter.notifyDataSetChanged()
//                }
//                .addOnFailureListener {
//                    Log.d("check ","check error $it")
//                }



        // còn cái này nữa b ê
        // h bạn muốn hiểu nó ntn
        // hỏi full code đoạn này luôn, t k hiểu all
        /*

         */
//        fireStore = FirebaseFirestore.getInstance()  // dòng này là để khởi tạo cho firebase
//        val docRef = fireStore.collection("quiz").document("aa")
//        docRef.addSnapshotListener { snapshot, e ->
//            if (e != null) {
//                Log.w("exits", "Listen failed.", e)
//                return@addSnapshotListener
//            }
//
//            if (snapshot != null && snapshot.exists()) {
//                Log.d("exits", "Current data: ${snapshot.data}")
//            } else {
//                Log.d("exits", "Current data: null")
//            }
//            Log.d("DATA", snapshot?.toObject(Quiz::class.java).toString())
//            quizList.clear()
//
//            //quizList.addAll(snapshot.get("quizz", Quiz::class.java))
//            adapter.notifyDataSetChanged()
        //}

//    private fun loadPostData() {
//
//    }


        /*
        đây c ơi, chỗ này bị lỗi này, t k hiểu sao lỗi, debug nó báo null ở đây này
         */
        fireStore = FirebaseFirestore.getInstance() // firebase: null
        val collectionReference = fireStore.collection("quiz")

        collectionReference.addSnapshotListener { value, error ->

            if (value == null || error != null) {
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            Log.d("DATA", value.toObjects(Quiz::class.java).toString());
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java)) // vào đây thì debug ko chạy tiếp được nữa
            adapter.notifyDataSetChanged()

        }

        //nó sinh ra ntn nhỉ
        // hay là do mặc định của kotlin về cái đó
        // t k hiểu cái addSnapshotListenner b ạ// nó là lệnh mặc định r
       //oke tạm hieeir đoạn này là để lấy dữ liệu về đi
        //chả thấy chỗ nó đẩy lên đâu - bài này chưa xogn đâu bạn ạ :(

    }

    private fun setUpRecycleView() {
        adapter = QuizAdapter(this, quizList)
        quizRecycleView.layoutManager = GridLayoutManager(this, 2)
        quizRecycleView.adapter = adapter
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(appBar) // thanh công cụ // có thấy thanh công cụ đâu.. ko đây là phần khác bạn ơi, trong manifest t cho chạy mỗi cái quiz kia
        actionBarDrawerToggle = ActionBarDrawerToggle(this,
                mainDrawer, R.string.app_name, R.string.app_name
        )
        actionBarDrawerToggle.syncState()
    }

    //chỉ định options menu cho một activity, chúng ta override phương thức
    // click vao icon ==> drawer menu
    //Phương thức này truyền menu item nào được chọn.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}