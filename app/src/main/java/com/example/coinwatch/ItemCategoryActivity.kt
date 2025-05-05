package com.example.coinwatch

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.coinwatch.DatabaseHelper

class ItemCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_category)

        // Get references to the UI elements
        val etAmount = findViewById<EditText>(R.id.etExpenseAmount)
        val etDate = findViewById<EditText>(R.id.etExpenseDate)
        val etDescription = findViewById<EditText>(R.id.etExpenseDescription)
        val spinnerCategory = findViewById<Spinner>(R.id.spinnerCategory)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)

        // Button click to save expense to database
        btnAddExpense.setOnClickListener {
            val amount = etAmount.text.toString()
            val date = etDate.text.toString()
            val description = etDescription.text.toString()
            val category = spinnerCategory.selectedItem.toString()

            // Save to the database
            val databaseHelper = DatabaseHelper(this)
            databaseHelper.addExpense(amount, date, description, category)

            // Optionally, return to the dashboard
            finish()
        }
    }
}
