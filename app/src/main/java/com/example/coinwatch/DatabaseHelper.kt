package com.example.coinwatch

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "CoinWatchDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "email TEXT UNIQUE," +
                    "password TEXT)"
            //change 1
        )

        db?.execSQL(
            "CREATE TABLE expenses (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "amount TEXT," +
                    "date TEXT," +
                    "description TEXT," +
                    "category TEXT," +
                    "user_email TEXT," +
                    "FOREIGN KEY(user_email) REFERENCES users(email))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS users")
        db?.execSQL("DROP TABLE IF EXISTS expenses")
        onCreate(db)
    }

    // Insert a new user
    fun insertUser(email: String, password: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("email", email)
            put("password", password)
        }
        val result = db.insert("users", null, contentValues)
        return result != -1L
    }

    // Check if user exists with email and password
    fun checkUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM users WHERE email = ? AND password = ?",
            arrayOf(email, password)
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    // Insert a new expense for a user
    fun insertExpense(amount: String, date: String, description: String, category: String, userEmail: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("amount", amount)
            put("date", date)
            put("description", description)
            put("category", category)
            put("user_email", userEmail)
        }
        val result = db.insert("expenses", null, contentValues)
        return result != -1L

        //change 1
    }

    // Fetch all expenses for a given user email
    fun getUserExpenses(userEmail: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery(
            "SELECT * FROM expenses WHERE user_email = ?",
            arrayOf(userEmail)
        )
    }
}
