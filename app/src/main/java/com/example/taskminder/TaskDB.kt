package com.example.taskminder

import android.provider.BaseColumns

class tasksConnect {
    abstract class tasksDBC: BaseColumns {
        companion object {
            val TABLE_NAME = "etudiant"
            val COLUMN_NAME_NOM = "nom"
            val COLUMN_NAME_PRENOM = "prenom"
            private val TEXT_TYPE = " TEXT"
            private val COMMA_SEP = ","
            public val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_NAME " +
                    "(_ID INTEGER PRIMARY KEY $COMMA_SEP $COLUMN_NAME_NOM TEXT $COMMA_SEP " +
                    "$COLUMN_NAME_PRENOM TEXT)"
            public val SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS $TABLE_NAME"
        }
    }
}
