package com.abhishek.profiledata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhishek.profiledata.ui.theme.ProfileDataTheme

class MainActivity : ComponentActivity() {

    val mainViewModel: ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val navController = rememberNavController()
                  NavHost(navController = navController, startDestination = "profile_data") {
                   composable("profile_data"){ ProfileData(mainViewModel = mainViewModel,navController) }
                  composable("profile_screen"){ ProfileScreen(mainViewModel = mainViewModel, navController) }
                      composable("home_screen"){ HomeScreen() }
                }
                }
            }
        }
    }
}
