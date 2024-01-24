package com.abhishek.profiledata

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(profileViewModel: ProfileViewModel,
                navController:NavController) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
            imageUri = it
        }

    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,),
            title = { Text(text = "Edit Profile",color = Color.Magenta,) })
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

            ) {
            Column {
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalArrangement = Arrangement.Center){
                    Button(onClick = { launcher.launch("image/*") }) {
                        Text(text = "+",
                            fontWeight = FontWeight.Bold)
                    }
                    imageUri?.let {
                        bitmap = if (Build.VERSION.SDK_INT < 28){
                          MediaStore.Images.Media.getBitmap(context.contentResolver,it)
                        } else {
                            val source = ImageDecoder.createSource(context.contentResolver,it)
                            ImageDecoder.decodeBitmap(source)
                        }
                        Image(bitmap = bitmap?.asImageBitmap()!!, contentDescription = "",
                            modifier = Modifier.size(200.dp))
                            
                    }
                }
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = profileViewModel.profile.value.name ,
                    onValueChange = {
                        profileViewModel.profile.value = profileViewModel.profile.value.copy(
                            name = it
                        ) },
                    label = { Text("Name") },

                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = profileViewModel.profile.value.age,
                    onValueChange = {
                        profileViewModel.profile.value = profileViewModel.profile.value.copy(
                        age = it)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Age") }
                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = profileViewModel.profile.value.address,
                    onValueChange = {profileViewModel.profile.value = profileViewModel.profile.value.copy(
                        address = it)},
                    label = { Text("Address") }
                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = profileViewModel.profile.value.email,
                    onValueChange = {profileViewModel.profile.value = profileViewModel.profile.value.copy(
                        email = it)},
                    label = { Text("Email") }
                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(10f),
                    value = profileViewModel.profile.value.mobile,
                    onValueChange = { profileViewModel.profile.value = profileViewModel.profile.value.copy(
                        mobile = it)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Mobile") }
                )
            }
        }
        Spacer(modifier = Modifier.heightIn(10.dp))
        Button(onClick = {
            profileViewModel.save() },

            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
        ) {
            Text(text = "Save", color = Color.White )
        }
        Spacer(modifier = Modifier.heightIn(10.dp))
        Button(onClick = {
            navController.navigate("home_screen")
            profileViewModel.getProfileData()
                         },

            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
        ) {
            Text(text = "Next", color = Color.White )
        }
    }
}