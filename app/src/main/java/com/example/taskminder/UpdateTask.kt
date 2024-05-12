package com.example.taskminder

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class UpdateTask : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")

        val nomEditText = findViewById<EditText>(R.id.nom)
        val prenomEditText = findViewById<EditText>(R.id.Prenom)

        nomEditText.setText(nom)
    prenomEditText.setText(prenom)

        val connexion = findViewById<Button>(R.id.modifier)

        connexion.setText("Update")

        connexion.setOnClickListener {
            val name = nom
            val values = ContentValues()
            values.put(tasksConnect.tasksDBC.COLUMN_NAME_NOM, nomEditText.text.toString())
           values.put(tasksConnect.tasksDBC.COLUMN_NAME_PRENOM, prenomEditText.text.toString())

            val mDbHelper = DBHelper(applicationContext)
            val db = mDbHelper.writableDatabase

            val selection = "${tasksConnect.tasksDBC.COLUMN_NAME_NOM} = ?"
            val selectionArgs = arrayOf(nom)

            val updatedRows = db.update(
                tasksConnect.tasksDBC.TABLE_NAME,
                values,
                selection,
                selectionArgs
            )

            db.close()
            mDbHelper.close()

            if (updatedRows > 0) {
                val intent = Intent(this, TaskList::class.java)
                startActivity(intent)
            }
        }
    }
}