package com.abhishek.profiledata

import android.annotation.SuppressLint
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileData(mainViewModel: ProfileViewModel) {

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

            ) {

            Column {
                TextField(
                    value = mainViewModel.name.value ,
                    onValueChange = {
                        mainViewModel.name.value = it },
                    label = { Text("Name") }
                )
                TextField(
                    value = mainViewModel.age.value.toString(),
                    onValueChange = {
                        mainViewModel.age.value = it.toInt()},
                    label = { Text("Age") }
                )
                TextField(
                    value = mainViewModel.address.value,
                    onValueChange = {mainViewModel.address.value = it},
                    label = { Text("Address") }
                )
                TextField(
                    value = mainViewModel.email.value,
                    onValueChange = {mainViewModel.email.value = it},
                    label = { Text("Email") }
                )
                TextField(
                    value = mainViewModel.mobile.value,
                    onValueChange = { mainViewModel.mobile.value = it},
                    label = { Text("Mobile") }
                )
            }
        }
        Spacer(modifier = Modifier.heightIn(30.dp))
        Button(onClick = { mainViewModel.save() },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
        ) {
            Text(text = "Save", color = Color.White )

        }
    }
}