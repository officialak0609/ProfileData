package com.abhishek.profiledata

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    profileViewModel: ProfileViewModel,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
            title = { Text(text = "Home Screen", color = Color.Magenta) })

            LazyColumn {
                items(profileViewModel.userList.value) {
                    ProfileCard(it)
                    Log.e("Inside Lazycolumn", it.toString())
                    Text(
                        text = "Name:- ${it.name}",
                        fontSize = 18.sp,

                    )
                    Text(
                        text = "Email:- ${it.email}",
                        fontSize = 15.sp,
                    )
                }
            }
        }
    }

@Composable
fun ProfileCard(profile: Profile,
                profileViewModel: ProfileViewModel) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_avtar),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp)
            )
            Column {
                Text(
                    text = "Name:- ${it.name}",
                    fontSize = 18.sp,

                    )
                Text(
                    text = "Email:- ${it.email}",
                    fontSize = 15.sp,
                )
            }
        }
    }
}