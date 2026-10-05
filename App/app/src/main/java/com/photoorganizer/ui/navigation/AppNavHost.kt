package com.photoorganizer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.photoorganizer.ui.screens.camera.CameraScreen
import com.photoorganizer.ui.screens.home.HomeScreen
import com.photoorganizer.ui.screens.photo.PhotoDetailScreen
import com.photoorganizer.ui.screens.tags.TagsScreen
import com.photoorganizer.ui.viewmodel.PhotoViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val viewModel: PhotoViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onOpenCamera = { navController.navigate("camera") },
                onOpenTags = { navController.navigate("tags") },
                onOpenPhoto = { photoId -> navController.navigate("photo/$photoId") }
            )
        }
        composable("camera") {
            CameraScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("tags") {
            TagsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("photo/{photoId}") { backStackEntry ->
            val photoId = backStackEntry.arguments?.getString("photoId") ?: return@composable
            PhotoDetailScreen(
                photoId = photoId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
