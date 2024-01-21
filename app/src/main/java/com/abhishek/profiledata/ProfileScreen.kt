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
                    value = mainViewModel.profile.value.name,
                    onValueChange = {
                        mainViewModel.profile.value = mainViewModel.profile.value },
                    label = { Text("Name") }
                )
                TextField(
                    value = mainViewModel.profile.value.age,
                    onValueChange = {
                        mainViewModel.profile.value = mainViewModel.profile.value},
                    label = { Text("Age") }
                )
                TextField(
                    value = mainViewModel.profile.value.address,
                    onValueChange = {mainViewModel.profile.value= mainViewModel.profile.value},
                    label = { Text("Address") }
                )
                TextField(
                    value = mainViewModel.profile.value.email,
                    onValueChange = {mainViewModel.profile.value = mainViewModel.profile.value},
                    label = { Text("Email") }
                )
                TextField(
                    value = mainViewModel.profile.value.mobile,
                    onValueChange = { mainViewModel.profile.value = mainViewModel.profile.value},
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
            Text(text = "Fetch Data", color = Color.White )

        }

        Spacer(modifier = Modifier.heightIn(30.dp))
        Button(onClick = {
            mainViewModel.getProfileData() },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
        ) {
            Text(text = "Home", color = Color.White)
        }
    }
}