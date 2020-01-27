package com.infotech.mvparch.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.infotech.mvparch.model.ToDo

class ToDoListDBAdapter(context: Context) {

    private val TAG = ToDoListDBAdapter::class.java.simpleName

    private val DB_NAME = "todolist.db"
    private val DB_VERSION = 2
    private val TABLE_TODO = "table_todo"
    private val COLUMN_TODO_ID = "task_id"
    private val COLUMN_TODO = "todo"
    private val COLUMN_PLACE = "place"

    //create table table_todo(task_id integer primary key, todo text not null);
//private static String CREATE_TABLE_TODO="CREATE TABLE "+TABLE_TODO+"("+COLUMN_TODO_ID+" INTEGER PRIMARY KEY, "+COLUMN_TODO+" TEXT NOT NULL)";
    private val CREATE_TABLE_TODO =
        ("CREATE TABLE " + TABLE_TODO + "(" + COLUMN_TODO_ID + " INTEGER PRIMARY KEY, " + COLUMN_TODO + " TEXT NOT NULL, "
                + COLUMN_PLACE + " TEXT NOT NULL)")

    private var context: Context? = null
    private var sqLliteDatabase: SQLiteDatabase? = null
    private var toDoListDBAdapterInstance: ToDoListDBAdapter? = null

    init {
        this.context = context
        sqLliteDatabase =
            ToDoListDBHelper(this.context, DB_NAME, null, DB_VERSION).writableDatabase
    }

    fun getToDoListDBAdapterInstance(context: Context): ToDoListDBAdapter? {
        if (toDoListDBAdapterInstance == null) {
            toDoListDBAdapterInstance = ToDoListDBAdapter(context)
        }
        return toDoListDBAdapterInstance
    }

    //insert,delete,modify,query methods

    //insert,delete,modify,query methods
    fun insert(toDoItem: String?): Boolean {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TODO, toDoItem)
        return sqLliteDatabase!!.insert(TABLE_TODO, null, contentValues) > 0
    }

    fun insert(toDoItem: String?, place: String?): Boolean {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TODO, toDoItem)
        contentValues.put(COLUMN_PLACE, place)
        return sqLliteDatabase!!.insert(TABLE_TODO, null, contentValues) > 0
    }

    fun delete(taskId: Int): Boolean {
        return sqLliteDatabase!!.delete(TABLE_TODO, "$COLUMN_TODO_ID = $taskId", null) > 0
    }

    fun modify(taskId: Int, newToDoItem: String?): Boolean {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TODO, newToDoItem)
        return sqLliteDatabase!!.update(
            TABLE_TODO,
            contentValues,
            "$COLUMN_TODO_ID = $taskId",
            null
        ) > 0
    }

    fun getAllToDos(): List<ToDo> {
        val toDoList: MutableList<ToDo> = ArrayList()
        val cursor: Cursor = sqLliteDatabase!!.query(
            TABLE_TODO,
            arrayOf(COLUMN_TODO_ID, COLUMN_TODO, COLUMN_PLACE),
            null,
            null,
            null,
            null,
            null,
            null
        )

        if ( cursor.getCount() > 0 ) {
            while (cursor.moveToNext()) {
                val toDo = ToDo(cursor.getLong(0), cursor.getString(1), cursor.getString(2))
                toDoList.add(toDo)
            }
        }

        cursor.close()
        return toDoList
    }

    private inner class ToDoListDBHelper(
        context: Context?,
        databaseName: String?,
        factory: CursorFactory?,
        dbVersion: Int
    ) :
        SQLiteOpenHelper(context, databaseName, factory, dbVersion) {
        override fun onConfigure(db: SQLiteDatabase) {
            super.onConfigure(db)
            db.setForeignKeyConstraintsEnabled(true)
            Log.v(TAG, "Inside onConfigure")
        }

        override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_TODO)
            Log.v(TAG, "Inside onCreate")
        }

        override fun onUpgrade(
            sqLiteDatabase: SQLiteDatabase,
            oldVersion: Int, newVersion: Int
        ) {
            Log.v(TAG, "Inside onUpgrade")
            when (newVersion) {
                2 -> sqLiteDatabase.execSQL("ALTER TABLE $TABLE_TODO ADD COLUMN $COLUMN_PLACE TEXT ")
                else -> {
                }
            }
        }
    }
}