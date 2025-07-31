package com.example.newstrata


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newstrata.myviewmodel.GoogleSignInViewModel
import com.example.newstrata.HomeScreen
import com.example.newstrata.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val googleSignInViewModel = GoogleSignInViewModel()

    NavHost(navController = navController, startDestination = "GreetingScreen") {
        composable("GreetingScreen") {
            GreetingScreen(navController)
        }

        composable(route = "HomeScreen") {
            HomeScreen {
                googleSignInViewModel.handleGoogleSignIn(context, navController)
            }
        }

        composable(route = "ProfileScreen") {
            ProfileScreen(googleSignInViewModel)
        }
        composable("SurveyScreen") {
            SurveyScreen(navController)
        }
        composable("DashboardScreen") {
            DashboardScreen(navController)


        }

    }
}