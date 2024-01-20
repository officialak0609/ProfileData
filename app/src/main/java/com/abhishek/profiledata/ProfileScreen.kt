package com.abhishek.profiledata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    mainViewModel: ProfileViewModel,
    navController: NavHostController
             ) {
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,),
            title = { Text(text = "Profile Screen",color = Color.Magenta,) },)
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

            ) {

            Column {
                TextField(
                    value = mainViewModel.viewProfile.value.name,
                    onValueChange = {
                        mainViewModel.viewProfile.value = mainViewModel.profile.value.copy(
                            name = it) },
                    label = { Text("Name") }
                )
                TextField(
                    value = mainViewModel.viewProfile.value.age,
                    onValueChange = {
                        mainViewModel.viewProfile.value = mainViewModel.profile.value.copy(
                            age = it)},
                    label = { Text("Age") }
                )
                TextField(
                    value = mainViewModel.viewProfile.value.address,
                    onValueChange = {mainViewModel.viewProfile.value= mainViewModel.profile.value.copy(
                        address = it)},
                    label = { Text("Address") }
                )
                TextField(
                    value = mainViewModel.viewProfile.value.email,
                    onValueChange = {mainViewModel.viewProfile.value = mainViewModel.profile.value.copy(
                            email = it)},
                    label = { Text("Email") }
                )
                TextField(
                    value = mainViewModel.viewProfile.value.mobile,
                    onValueChange = { mainViewModel.viewProfile.value = mainViewModel.profile.value.copy(
                        mobile = it)},
                    label = { Text("Mobile") }
                )
            }
        }
        Spacer(modifier = Modifier.heightIn(30.dp))
        Button(onClick = {
            mainViewModel.getProfileData() },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
        ) {
            Text(text = "Fatch", color = Color.White )

        }
    }
}