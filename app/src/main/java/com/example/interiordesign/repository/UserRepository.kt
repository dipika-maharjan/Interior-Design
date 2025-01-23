package com.example.interiordesign.repository

import com.example.interiordesign.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    //{
    //"success" : true
    //"message" : "Login successful"
    //"statusCode" : 200 int pani rakhnu parxa

    fun login(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) //unit=no return type

    fun signup(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) //string=message, string =userid

    fun addUserToDatabase(userId: String, userModel: UserModel, callback: (Boolean, String) -> Unit)

    fun forgetPassword(email: String, callback: (Boolean, String) -> Unit)

    fun getCurrentUser(): FirebaseUser?
}