package com.abhishek.profiledata
import ChatViewModel
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhishek.profiledata.ui.theme.ProfileDataTheme
import com.firebase.ui.auth.AuthUI

class MainActivity : ComponentActivity() {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerLoginLauncher()

        setContent {
            ProfileDataTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login_screen") {
                        composable("login_screen") {
                            LoginScreen(
                                launcherLoginFlow = ::launchLoginFlow,
                                profileViewModel = profileViewModel,
                                navController = navController
                            )
                        }
                       composable("chat_screen") {
                           ChatScreen(
                               chatViewModel = ChatViewModel(),
                               navController = navController
                           )
                        }
                        composable("edit_screen") {
                            EditProfileScreen(
                                profileViewModel = profileViewModel,
                                navController
                            )
                        }
                        composable("home_screen") {
                            UserListScreen(
                                profileViewModel = profileViewModel,
                                navController = navController
                            )
                        }
                        composable("login_screen") { LoginScreen(
                            launcherLoginFlow = ::launchLoginFlow,
                            profileViewModel = profileViewModel,
                            navController = navController
                        ) }
                    }
                }
            }
        }
    }

    private lateinit var loginLauncher: ActivityResultLauncher<Intent>
    private fun registerLoginLauncher() {
        Log.d("TAG", "Inside setupLoginLauncher")
        loginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            Log.d("TAG", "Inside ActivityResult $result")
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d("TAG", "Inside ResultLambda ")
                loginHandler()
            } else Toast.makeText(this, "Not able to Login, Try Again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun launchLoginFlow(loginHandler: (() -> Unit)) {
        this.loginHandler = loginHandler

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(
                listOf(
                    AuthUI.IdpConfig.GoogleBuilder().build()
                )
            )
            .build()

        loginLauncher.launch(intent)
    }

    private lateinit var loginHandler: (() -> Unit)
}
