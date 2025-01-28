package com.example.interiordesign.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.interiordesign.R
import com.example.interiordesign.databinding.ActivityForgetBinding
import com.example.interiordesign.repository.UserRepository
import com.example.interiordesign.repository.UserRepositoryImpl

class ForgetActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetBinding
    val userRepository: UserRepository = UserRepositoryImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityForgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding .resetButton.setOnClickListener {
            val email = binding.forgetEmail.text.toString()
            if(email.isEmpty()){
                binding.forgetEmail.error = "Email cannot be empty"
            }

            userRepository.forgetPassword(email){
                    success, message ->
                if(success){
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }

            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}