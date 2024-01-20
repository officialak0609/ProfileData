package com.abhishek.profiledata

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    var profile = mutableStateOf(Profile())
    var viewProfile = mutableStateOf(Profile())
    var profileData = UserServerRepo()

    fun save() {
        Log.e("ABC", "Inside View Model Function")
        profileData.saveUserData(
            name = profile.value.name,
            age = profile.value.age,
            email = profile.value.email,
            address = profile.value.address,
            mobileNo = profile.value.mobile
        )
    }

    fun getProfileData() {
        Log.e("ABC", "Inside View Model Function")
        viewModelScope.launch {
            if (viewProfile.value.email.isNotEmpty()) {
                Log.e ("ABCDE", "User Profile Get")

                viewProfile.value = profileData.getProfileData(profile.value.email)!!
            }
        }
    }
}

