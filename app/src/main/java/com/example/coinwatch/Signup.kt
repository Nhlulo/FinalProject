package com.example.coinwatch

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coinwatch.databinding.ActivitySignupBinding

class Signup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize view binding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        // Handle sign up button click
        binding.signupButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                val success = databaseHelper.insertUser(username, email, password)
                if (success) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Login::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Signup failed. Email may already be used.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Redirect to Login activity
        binding.loginRedirect.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
