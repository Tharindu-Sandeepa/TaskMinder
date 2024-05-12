package com.example.taskminder

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskList : AppCompatActivity() {
    private var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_list)

        dbHelper = DBHelper(this)
        val cursor: Cursor? = dbHelper?.readableDatabase?.query(
            "etudiant",
            arrayOf("_ID","nom", "prenom"),
            null,
            null,
            null,
            null,
            null
        )

        val back =findViewById<Button>(R.id.backButton)
        val recyclerView = findViewById<RecyclerView>(R.id.etudiants)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val adapter = cursor?.let { EtudiantAdapter(this, it) }
        recyclerView.adapter = adapter
    }



    override fun onDestroy() {
        super.onDestroy()
        dbHelper?.close()
    }
}
