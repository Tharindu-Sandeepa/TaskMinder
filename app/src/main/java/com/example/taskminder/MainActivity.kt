package com.example.taskminder

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.os.Handler
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Display the loading screen
        setContentView(R.layout.loading_screen)
        // Handler to delay transition to activity_main.xml
        Handler().postDelayed({
            // Switch to activity_main.xml after 3 seconds
            setContentView(R.layout.activity_main)
            initViews()
        }, 2000)
    }

    private fun initViews() {
        val nom = findViewById<EditText>(R.id.nom)
        val Prenom = findViewById<EditText>(R.id.Prenom)

        val connexion = findViewById<Button>(R.id.modifier)

        val buttonTaskList =findViewById<Button>(R.id.buttonTaskList)


        buttonTaskList.setOnClickListener {
            val intent = Intent(this, TaskList::class.java)
            startActivity(intent)
        }

        connexion.setOnClickListener {
            val values = ContentValues()
            values.put(tasksConnect.tasksDBC.COLUMN_NAME_NOM, nom.text.toString())
            values.put(tasksConnect.tasksDBC.COLUMN_NAME_PRENOM, Prenom.text.toString())


            val mDbHelper = DBHelper(applicationContext)
            val db = mDbHelper.writableDatabase
            var newRowId: Long = 0

            newRowId = db.insert(
                tasksConnect.tasksDBC.TABLE_NAME,
                null,
                values
            )
            db.close()
            mDbHelper.close()
            val intent = Intent(this, TaskList::class.java)
            startActivity(intent)
        }
    }
}
