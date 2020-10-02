package com.example.firebasenotificationtesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

const val TOPIC = "/topics/testTopic"

//internal lateinit var db:DatabaseHelper
//internal var lastHistory:List<History> = ArrayList<History>()

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        db = DatabaseHelper(this)


        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        btnHistory.setOnClickListener() {
            val intent = Intent(this, AllHistory::class.java)
            startActivity(intent)
            finish()
        }


        btnSend.setOnClickListener {
            val title = etTitle.text.toString()
            val message = etMessage.text.toString()
            if (title.isNotEmpty() && message.isNotEmpty()) {
                PushNotification(
                    NotificationData(title, message),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }

//            val history = History(
//                etTitle.text.toString(),
//                etMessage.text.toString()
//            )
//            db.addHistory(history)
//            refreshData()

        }

    }

//    private fun refreshData(){
//        lastHistory = db.allHistory
//        val adapter = ListHistoryAdapter(this, lastHistory, etTitle, etMessage)
////        lvHistory.adapter = adapter
//    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if (response.isSuccessful){
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else{
                Log.e(TAG, response.errorBody().toString())
            }
        } catch (e: Exception){
            Log.e(TAG, e.toString())
        }
    }

}