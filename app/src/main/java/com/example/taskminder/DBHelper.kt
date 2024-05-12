package com.example.taskminder

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PkFE.db"
    }
    private var taskDBHelper: DBHelper? = null

    fun getEtudiantDBHelper(context: Context): DBHelper {
        return taskDBHelper ?: synchronized(this) {
            val instance = DBHelper(context)
            taskDBHelper = instance
            instance
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(tasksConnect.tasksDBC.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(tasksConnect.tasksDBC.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}
