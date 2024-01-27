package com.abhishek.profiledata

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    launcherLoginFlow: (() -> Unit) -> Unit,
    profileViewModel: ProfileViewModel,
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            launcherLoginFlow {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {

                    Log.e("TAG", "FirebaseAuth :- ${user.email}")
                    Log.e("TAG", "FirebaseAuth :- ${user.displayName}")
                    Log.e("TAG", "FirebaseAuth :- ${user.photoUrl}")
                    Log.e("TAG", "FirebaseAuth :- ${user.providerId}")
                    Log.e("TAG", "FirebaseAuth :- ${user.uid}")

                    profileViewModel.profile.value = profileViewModel.profile.value.copy(
                        name = user.displayName!!,
                        email = user.email!!
                    )
                    Log.e("VALU", "Profile Value ${profileViewModel.profile.value}")

                    navController.navigate("edit_screen")
                }

            }
        }  ,colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = CutCornerShape(10.dp),
            border = BorderStroke(2.dp, color = Color.Red)
        )
        {
            Image(
            painter = painterResource(id = R.drawable.google_image),
            contentDescription = ""
        )
            Text(text = "Sign in with Google", color = Color.Black, modifier = Modifier.padding(6.dp)
                ,fontWeight = FontWeight.Bold,
                fontSize = 15.sp)

        }
    }
}