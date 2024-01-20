package com.abhishek.profiledata

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserServerRepo {
    val db = Firebase.firestore

    @SuppressLint("SuspiciousIndentation")
    fun saveUserData(name: String, age: Int, email: String, mobileNo: String, address: String) {
        Log.e("ABC","Inside Firebase Repo Function $email")
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

    suspend fun getProfileData(email: String): Profile? {
           val profile = db.collection("Profile").document("jaipur")
                .get()
                .addOnSuccessListener {
                    Log.d("ABC", "DocumentSnapshot Fetch successfully ! ${it.data}")
                }
                .addOnFailureListener { e -> Log.w("ABCD", "Error writing document", e) }
               .await()
               .toObject(Profile::class.java)

        return profile
    }
}