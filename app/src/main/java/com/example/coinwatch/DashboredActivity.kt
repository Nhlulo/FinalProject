package com.example.coinwatch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.coinwatch.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbored) // Ensure the correct layout file name

        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)
        val btnViewAnalytics = findViewById<Button>(R.id.btnViewAnalytics)
        val btnBudgetGoals = findViewById<Button>(R.id.btnBudgetGoals)
        val btnATMLocator = findViewById<Button>(R.id.btnATMLocator)
        val userEmail = intent.getStringExtra("email")

        // Navigate to ItemCategoryActivity when Add Expense button is clicked
        btnAddExpense.setOnClickListener {
            val intent = Intent(this, itemCategoryActivity::class.java)
            startActivity(intent)
        }
    }
}
