package com.example.firebasenotificationtesting

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object{
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "History.db"

        private val TABLE_NAME = "history"
        private val COL_ID = "id"
        private val COL_TITLE = "title"
        private val COL_MESSAGE = "message"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String = ("CREATE TABLE \"history\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"title\"\tTEXT,\n" +
                "\t\"message\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ")")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    val allHistory:List<History>
        get() {
        val lastHistory = ArrayList<History>()
        val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY $COL_ID DESC"
        val db = writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()){
            do {
                val history = History()
                history.title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                history.message = cursor.getString(cursor.getColumnIndex(COL_MESSAGE))

                lastHistory.add(history)
            }while (cursor.moveToNext())
        }
            db.close()
        return lastHistory
    }

    fun addHistory(history: History){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_TITLE,history.title)
        values.put(COL_MESSAGE, history.message)

        db.insert(TABLE_NAME, null, values)
        db.close()

    }

    fun showHistory(): Cursor? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        return cursor
    }


}