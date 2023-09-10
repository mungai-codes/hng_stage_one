package com.mungai_codes.hngstageone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mungai_codes.hngstageone.presentation.github.GithubScreen
import com.mungai_codes.hngstageone.presentation.main.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_screen") {
        composable(route = "main_screen") {
            MainScreen(navController = navController)
        }
        composable(route = "github_screen") {
            GithubScreen()
        }
    }
}