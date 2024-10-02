@file:OptIn(ExperimentalMaterial3Api::class)

package com.mluzzi.bookshelfapp.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mluzzi.bookshelfapp.R

@Composable
fun HomeScreen(navController: NavHostController, bookshelfViewModel: BookshelfViewModel) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = {
                Text(
                    stringResource(R.string.digite_sua_busca_aqui),
                    fontSize = 18.sp
                )
            },
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Button(
            onClick = {
                navController.navigate("result/$text")
                bookshelfViewModel.getBooks(text)
            },
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                stringResource(R.string.buscar),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}


//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(navController = NavHostController(LocalContext.current))
//}