package com.example.firebasenotificationtesting

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_all_history.*
import kotlinx.android.synthetic.main.activity_main.*

internal lateinit var db:DatabaseHelper
internal var lastHistory:List<History> = ArrayList<History>()

class AllHistory : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_history)


        lateinit var listItem: ArrayList<String>
        var lastHistory:List<History> = ArrayList<History>()

        db = DatabaseHelper(this)


        viewData()


        btnHome.setOnClickListener () {
            val intent = Intent(this, MainActivity::class.java)
            db.close()
            startActivity(intent)
        }

        lvHistory.setOnItemClickListener{parent, view, position, id ->
            Toast.makeText(this, position, Toast.LENGTH_SHORT).show()

        }


    }


    private fun viewData(){
        lastHistory = db.allHistory
        val adapter = ListHistoryAdapter(this, lastHistory)
        lvHistory.adapter = adapter
        db.close()
    }

}