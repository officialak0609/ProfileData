package com.abhishek.profiledata

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    var name = mutableStateOf("")
    var age = mutableStateOf(0)
    var email = mutableStateOf("")
    var mobile = mutableStateOf("")
    var address = mutableStateOf("")
    var profileData = UserProfileData()

    fun save() {
        Log.e("ABC", "Inside View Model Function")
        profileData.saveUserData(
            name = name.value,
            age = age.value,
            email = email.value,
            address = address.value,
            mobileNo = mobile.value
        )
    }
}

