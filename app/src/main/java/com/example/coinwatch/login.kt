package com.example.coinwatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginBtn = findViewById<Button>(R.id.loginButton)
        val emailInput = findViewById<EditText>(R.id.emailEditText)
        val passwordInput = findViewById<EditText>(R.id.passwordEditText)
        val signup = findViewById<Button>(R.id.signUpRedirect)

        val db = DatabaseHelper(this)

        // Login button logic
        loginBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val isValidUser = db.checkUser(email, password)

            if (isValidUser) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Sign up redirect logic
        signup.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }
    }
}
