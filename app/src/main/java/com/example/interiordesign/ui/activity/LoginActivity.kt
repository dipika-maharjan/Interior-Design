package com.example.interiordesign.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.interiordesign.R
import com.example.interiordesign.databinding.ActivityLoginBinding
import com.example.interiordesign.repository.UserRepositoryImpl
import com.example.interiordesign.viewModel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)

        binding.loginButton.setOnClickListener {
            //show
            var email :String = binding.loginEmail.text.toString()
            var password :String = binding.loginPassword.text.toString()

            userViewModel.login(email,password){
                    success,message->
                if(success){
                    //dismiss
                    Toast.makeText(this@LoginActivity,message, Toast.LENGTH_LONG).show()

                    var intent = Intent(this@LoginActivity,NavigationActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    //dismiss
                    Toast.makeText(applicationContext,message, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity,
                RegistrationActivity::class.java)
            startActivity(intent)

        }
        

        binding.btnForget.setOnClickListener {
            val intent = Intent(this@LoginActivity,
                ForgetActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val intent = Intent(this@LoginActivity,
                NavigationActivity::class.java)
            startActivity(intent)

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}