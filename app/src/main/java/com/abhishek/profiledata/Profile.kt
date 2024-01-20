package com.abhishek.profiledata

import androidx.compose.runtime.mutableStateOf

data class Profile(
    var name : String = "",
    var age : String = "",
    var email : String = "",
    var mobile : String = "",
    var address : String = ""
)
