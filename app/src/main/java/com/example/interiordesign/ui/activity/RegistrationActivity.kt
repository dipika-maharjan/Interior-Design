package com.example.interiordesign.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.interiordesign.R
import com.example.interiordesign.databinding.ActivityRegistrationBinding
import com.example.interiordesign.model.UserModel
import com.example.interiordesign.repository.UserRepositoryImpl
import com.example.interiordesign.utils.LoadingUtils
import com.example.interiordesign.viewModel.UserViewModel

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    lateinit var userViewModel: UserViewModel
    lateinit var loadingUtils: LoadingUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingUtils = LoadingUtils(this)

        var repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo) //object

        binding.registerButton.setOnClickListener {
            loadingUtils.show()
            val fullname = binding.fullname.text.toString()
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()
            val confirmpassword = binding.confirmPassword.text.toString()

            userViewModel.signup(email, password) { success, message, userId ->
                if (success) {
                    var userModel =
                        UserModel(userId.toString(), fullname, email, password, confirmpassword)
                    userViewModel.addUserToDatabase(userId, userModel) { success, message ->
                        if (success) {
                            Toast.makeText(this@RegistrationActivity, message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                } else {
                    loadingUtils.dismiss()
                    Toast.makeText(this@RegistrationActivity, message, Toast.LENGTH_LONG).show()
                }
            }

            binding.btnSignup.setOnClickListener {
                val intent = Intent(this@RegistrationActivity,
                    LoginActivity::class.java)
                startActivity(intent)

            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}