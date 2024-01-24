package com.abhishek.profiledata

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

class UserServerRepo {
    val db = Firebase.firestore
    @SuppressLint("SuspiciousIndentation")
    fun saveUserData(name: String, age: String, email: String, mobileNo: String, address: String) {
        Log.e("ABC","Inside Firebase Repo Function $email")
        val data = Profile(name, age, email, address, mobileNo)
            db.collection("Profiles")
                .document(email).set(data)
                .addOnSuccessListener { Log.e("ABCD", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.e("ABCDE", "Error writing document", e) }

    }

    suspend fun getProfileData(): List<Profile> {
           return db.collection("Profiles")
                .get()
                .addOnSuccessListener {
                    Log.d("ABC", "DocumentSnapshot Fetch successfully ! ${it.documents.size}")

                }
                .addOnFailureListener { e -> Log.w("ABCD", "Error writing document", e) }
               .await()
               .toObjects(Profile::class.java)
    }
}