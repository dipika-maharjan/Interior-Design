package com.example.interiordesign.repository

import com.example.interiordesign.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRepositoryImpl : UserRepository {
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    var database : FirebaseDatabase = FirebaseDatabase.getInstance() //table instance
    var ref : DatabaseReference = database.reference.child("users") //database instance and root access vayo and .child helps to access the element inside root

    override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true, "Login successful")
            }else{
                callback(false, it.exception?.message.toString())
            }
        }
    }

    override fun signup(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true, "Registration success", auth.currentUser?.uid.toString())
            }else{
                callback(false, it.exception?.message.toString(), " ")
            }
        }
    }

    override fun addUserToDatabase(
        userId: String,
        userModel: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userId).setValue(userModel).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true, "Registration success")
            }else{
                callback(false, it.exception?.message.toString())
            }
        }
    }

    override fun forgetPassword(email: String, callback: (Boolean, String) -> Unit) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true, "Reset email sent to $email")
            }else{
                callback(false, it.exception?.message.toString())
            }
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}