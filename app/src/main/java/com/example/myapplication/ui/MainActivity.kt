package com.example.myapplication.ui

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*



import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.Pho

import com.example.myapplication.viewmodel.MyViewModel
import com.example.myapplication.data.TabItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.PhotoViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Myapp()
                }
            }
        }
    }
}

@Composable
fun Myapp() {
    val items = listOf(
        TabItem("Home",Icons.Filled.Home,"Homepage"),
        TabItem("Calculate",Icons.Filled.PlayArrow,"Calculatepage")
    )
    MainScreen(items)
}

@Composable
fun MainScreen(items:List<TabItem>) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopAppBar() { Text(text = "My App") }},
        content = { MyNavController(navController = navController)},
        bottomBar = {MyBottomNavigation(items,navController)}
    )
}

@Composable
fun MyBottomNavigation(items: List<TabItem>, navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    BottomNavigation{
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected =selectedItem ==index ,
                onClick = {
                    selectedItem =index
                    navController.navigate(item.route)},
                icon = {Icon(item.icon,contentDescription = null)},
                label = { Text(item.label)}
            )
        }

    }

}

@Composable
fun Homepage(photoViewModel: PhotoViewModel= viewModel()) {
    PhotoList(photoViewModel.photos)
}

@Composable
fun PhotoList(photos:List<Pho>) {
    LazyColumn(

        modifier = Modifier.padding(10.dp)
                           .background(MaterialTheme.colors.background)

    ){
        items(photos){pho->
            Image(
                painter = rememberAsyncImagePainter(pho.download_url),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
            )
            Text(
                text ="Author :"+pho.author,
                color=Color.Magenta,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                textAlign = TextAlign.Center,


            )
            Divider(color = Color.Red, thickness = 3.dp)
        }
    }

}
@Composable
fun Calculatepage(myViewModel: MyViewModel = viewModel()) {

    Column(modifier = Modifier.padding(50.dp)) {
        Text(
            text = "BODY MASS INDEX",
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        )

        OutlinedTextField(
            value=myViewModel.heightInput,
            onValueChange ={myViewModel.changeHeightInput(it)},
            label = { Text("Height")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value= myViewModel.weightInput,
            onValueChange = {myViewModel.changeWeightInput(it)},
            label = { Text("Weight")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text(
            text = ("Body mass index is "+myViewModel.bmi),
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
            )


    }

}


@Composable
fun MyNavController(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination ="Homepage" ){
        composable(route = "Homepage"){
            Homepage()
        }
        composable(route = "Calculatepage"){
            Calculatepage()
        }
    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Myapp()
    }
}