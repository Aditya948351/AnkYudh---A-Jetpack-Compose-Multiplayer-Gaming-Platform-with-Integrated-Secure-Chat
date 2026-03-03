package com.example.ankyudh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ankyudh.ui.components.FluidBottomNavigation
import com.example.ankyudh.ui.screens.ChatListScreen
import com.example.ankyudh.ui.screens.ChatScreen
import com.example.ankyudh.ui.screens.GameDetailScreen
import com.example.ankyudh.ui.screens.GameplayScreen
import com.example.ankyudh.ui.screens.GamesScreen
import com.example.ankyudh.ui.screens.ProfileScreen
import com.example.ankyudh.ui.theme.AnkYudhTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnkYudhTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val showBottomNav = currentRoute in listOf("games", "chat", "profile")

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = if (showBottomNav) 80.dp else 0.dp)
                        ) {
                            NavHost(navController = navController, startDestination = "games") {
                            composable("chat") { 
                                ChatListScreen(onNavigateToChat = { name -> 
                                    navController.navigate("chat_detail/$name")
                                })
                            }
                            composable("chat_detail/{contactName}") { backStackEntry ->
                                val name = backStackEntry.arguments?.getString("contactName") ?: "Unknown"
                                ChatScreen(contactName = name, onNavigateBack = { navController.popBackStack() })
                            }
                            composable("games") { GamesScreen(onNavigateToGame = { navController.navigate("game_detail") }) }
                            composable("profile") { ProfileScreen() }
                            composable("game_detail") {
                                GameDetailScreen(
                                    onNavigateBack = { navController.popBackStack() },
                                    onJoinRoom = { navController.navigate("gameplay") }
                                )
                            }
                            composable("gameplay") {
                                GameplayScreen(
                                    onExitGame = { navController.popBackStack("games", inclusive = false) }
                                )
                            }
                        } // End NavHost
                        } // End Padded Box
                        
                        if (showBottomNav) {
                            // Overlay the fluid bottom navigation
                            FluidBottomNavigation(
                                onNavigationSelected = { route -> 
                                    navController.navigate(route) {
                                        popUpTo("games") { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
