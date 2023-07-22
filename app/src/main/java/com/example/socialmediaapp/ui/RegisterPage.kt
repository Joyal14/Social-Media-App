package com.example.socialmediaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.socialmediaapp.MainActivity
import com.example.socialmediaapp.databinding.ActivityRegisterPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityRegisterPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }
        //lets get the users email and password
        binding.btnLogin.setOnClickListener {
            performSignIn()
        }

    }

    private fun performSignIn() {
       val email = binding.edtMail.text.toString()
        val password = binding.edtPass.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please fill the all the details",Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent =  Intent(this,LoginPage::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        baseContext,
                        "Successful.",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error Occurred ${it.localizedMessage}",Toast.LENGTH_SHORT)
                    .show()
            }
    }
}