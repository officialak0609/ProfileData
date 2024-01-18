package com.abhishek.profiledata

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserProfileData {
    val db = Firebase.firestore

    @SuppressLint("SuspiciousIndentation")
    fun saveUserData(name: String, age: Int, email: String, mobileNo: String, address: String) {
        Log.e("ABC","Inside Firebase Repo Function")
        val data = hashMapOf(
            "Name" to name,
            "Age" to age,
            "Email Id" to email,
            "Mobile No." to mobileNo,
            "Address" to address
        )
            db.collection("Profiles")
                .document(email).set(data)
                .addOnSuccessListener { Log.e("ABCD", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.e("ABCDE", "Error writing document", e) }
    }

    fun getProfileData() {
            db.collection("Profile").document("User")
                .get()
                .addOnSuccessListener {
                    Log.d("ABC", "DocumentSnapshot successfully written!")
                }
                .addOnFailureListener { e -> Log.w("ABCD", "Error writing document", e) }

    }
}