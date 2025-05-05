package com.example.coinwatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val db = DatabaseHelper(this)

        val emailInput = findViewById<EditText>(R.id.emailEditText)
        val passwordInput = findViewById<EditText>(R.id.passwordEditText)
        val signUpBtn = findViewById<Button>(R.id.signupButton)

        signUpBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val success = db.insertUser(email, password)
            if (success) {
                Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, login::class.java))
                finish()
            } else {
                Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
