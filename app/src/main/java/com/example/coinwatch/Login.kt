package com.example.coinwatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signUpRedirect = findViewById<TextView>(R.id.signUpRedirect)
        val lLogin = findViewById<Button>(R.id.loginButton)

        lLogin.setOnClickListener {

            val intentu = Intent(this,DashboardActivity::class.java)
            startActivity(intentu)

        }

        signUpRedirect.setOnClickListener {
            try {
                val intent = Intent(this, Signup::class.java)
                startActivity(intent)
                // Remove finish() to keep Login in back stack
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error opening Sign Up", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
