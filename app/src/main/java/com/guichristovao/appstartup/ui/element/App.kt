package com.guichristovao.appstartup.ui.element

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guichristovao.appstartup.Routes.profile
import com.guichristovao.appstartup.profile.ui.element.ProfileScreen

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = profile) {
        composable(profile) { ProfileScreen() }
    }
}
