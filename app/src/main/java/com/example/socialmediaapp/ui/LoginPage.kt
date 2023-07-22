package com.example.socialmediaapp.ui

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.socialmediaapp.MainActivity
import com.example.socialmediaapp.R
import com.example.socialmediaapp.databinding.ActivitySignInPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivitySignInPageBinding

    var isAllFieldsChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val registerText : TextView = findViewById(R.id.tv_create)
        registerText.setOnClickListener {
                val intent = Intent(this, RegisterPage::class.java)
                startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {

            isAllFieldsChecked =  checkAllField()
            if(isAllFieldsChecked) {
                performLogin()
            }
        }

    }
    private fun checkAllField(): Boolean{
        if(binding.edtMail.length() == 0){
            binding.edtMail.error="This field is required"
            return false
        }
        if(binding.edtPassword.length() == 0){
            binding.edtPassword.error="This field is required"
            return false
        }
        return true
    }

    private fun performLogin() {
        //lets get the input from the users
        val email = binding.edtMail
        val password = binding.edtPassword

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Please fill the all the details", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent =  Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
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
    }
}