package com.example.coinwatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        // Find views by their IDs from the layout
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signUpRedirect = findViewById<Button>(R.id.signUpRedirect)

        // Login button logic
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                // Check the user credentials
                val isValidUser = databaseHelper.readUserByEmailAndPassword(email, password)

                if (isValidUser) {
                    // If login is successful, show success message and move to the DashboardActivity
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                    // Intent to navigate to DashboardActivity
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)

                    // Optionally finish the Login Activity so that the user cannot go back to it
                    finish()
                } else {
                    // If the credentials are incorrect, show an error message
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Handle any unexpected errors during login
                e.printStackTrace()
                Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // Redirect to Signup screen if user clicks "Sign Up"
        signUpRedirect.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
    }
}
