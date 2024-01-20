package com.abhishek.profiledata

import androidx.compose.runtime.mutableStateOf

data class Profile(
    var name : String = "",
    var age : Int = 0,
    var email : String = "",
    var mobile : String = "",
    var address : String = ""
)
