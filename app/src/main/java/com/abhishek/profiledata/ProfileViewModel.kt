package com.abhishek.profiledata

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    var profile = mutableStateOf(Profile())
    var viewProfile = mutableStateOf(Profile())
    var userServerRepo = UserServerRepo()

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

    fun getProfileData() {
        Log.e("ABC", "Inside View Model Function")
        viewModelScope.launch {
            userServerRepo.getProfileData(profile.value.email).also {
                if (it != null) {
                    viewProfile.value = it
                    Log.e("ABC", "View Model. Value ${viewProfile.value}")
                    Log.e("ABC", "It Value  ${it}")

                }

            }
        }
    }
}

