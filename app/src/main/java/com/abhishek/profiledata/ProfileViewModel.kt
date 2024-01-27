package com.abhishek.profiledata

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    var profile = mutableStateOf(Profile())
    private var userServerRepo = UserServerRepo()
    var userList = mutableStateOf(listOf(Profile()))
    var submit = mutableStateOf(false)

    fun save() {
        Log.e("ABC", "Inside View Model Function")
        userServerRepo.saveUserData(
            name = profile.value.name,
            age = profile.value.age,
            email = profile.value.email,
            address = profile.value.address,
            mobileNo = profile.value.mobile
        )
    }
    fun getProfileData(){
        viewModelScope.launch {
            Log.e("LIST", userList.value.toString())
            userServerRepo.getProfileData().also {
                userList.value = it
                Log.e("LIST", userList.value.toString())

            }

        }

    }
}


