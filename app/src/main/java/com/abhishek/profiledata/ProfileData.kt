package com.abhishek.profiledata

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileData(mainViewModel: ProfileViewModel,
                navController:NavController) {

    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,),
            title = { Text(text = "Profile Data",color = Color.Magenta,) },)
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

            ) {
            Column {
                Row (modifier = Modifier.fillMaxWidth()
                    .padding(20.dp), horizontalArrangement = Arrangement.Center){
                    Image(
                        painter = painterResource(id = R.drawable.abhishek),
                        contentDescription = "Image" ,contentScale = ContentScale.Crop,
                        alignment = Alignment.TopStart,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(40.dp)))
                }
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = mainViewModel.profile.value.name ,
                    onValueChange = {
                        mainViewModel.profile.value = mainViewModel.profile.value.copy(
                            name = it
                        ) },
                    label = { Text("Name") },

                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = mainViewModel.profile.value.age,
                    onValueChange = {
                        mainViewModel.profile.value = mainViewModel.profile.value.copy(
                        age = it)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Age") }
                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = mainViewModel.profile.value.address,
                    onValueChange = {mainViewModel.profile.value = mainViewModel.profile.value.copy(
                        address = it)},
                    label = { Text("Address") }
                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = mainViewModel.profile.value.email,
                    onValueChange = {mainViewModel.profile.value = mainViewModel.profile.value.copy(
                        email = it)},
                    label = { Text("Email") }
                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = mainViewModel.profile.value.mobile,
                    onValueChange = { mainViewModel.profile.value = mainViewModel.profile.value.copy(
                        mobile = it)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Mobile") }
                )
            }
        }
        Spacer(modifier = Modifier.heightIn(10.dp))
        Button(onClick = {
            mainViewModel.save() },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
        ) {
            Text(text = "Save", color = Color.White )

        }
        Spacer(modifier = Modifier.heightIn(10.dp))
        Button(onClick = {
            navController.navigate("Profile_screen") },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
        ) {
            Text(text = "Next", color = Color.White)
        }
    }
}