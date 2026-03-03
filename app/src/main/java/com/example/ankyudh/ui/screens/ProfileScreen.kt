package com.example.ankyudh.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var darkModeEnabled by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", fontWeight = FontWeight.Bold) }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 24.dp) // Avoid bottom bar
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Large Avatar
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(64.dp), tint = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Aditya Tyagi", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text("@aditya123", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedButton(onClick = { }, shape = RoundedCornerShape(20.dp)) {
                        Text("Edit Profile")
                    }
                }
            }
            
            item {
                // Stats Card
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem("Played", "128")
                        Divider(modifier = Modifier.height(40.dp).width(1.dp))
                        StatItem("Wins", "84")
                        Divider(modifier = Modifier.height(40.dp).width(1.dp))
                        StatItem("Win Rate", "65%")
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                // Leaderboard Badge
                ListItem(
                    headlineContent = { Text("Global Rank: #42", fontWeight = FontWeight.Bold) },
                    supportingContent = { Text("Top 5% of players") },
                    leadingContent = {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFD700).copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.EmojiEvents, contentDescription = "Rank", tint = Color(0xFFFFA000))
                        }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp).clip(RoundedCornerShape(12.dp)).background(MaterialTheme.colorScheme.surface)
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text("Settings", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                
                ListItem(
                    headlineContent = { Text("Dark Mode") },
                    leadingContent = { Icon(Icons.Default.DarkMode, contentDescription = null) },
                    trailingContent = { 
                        Switch(checked = darkModeEnabled, onCheckedChange = { darkModeEnabled = it }) 
                    }
                )
                
                ListItem(
                    headlineContent = { Text("Push Notifications") },
                    leadingContent = { Icon(Icons.Default.Notifications, contentDescription = null) },
                    trailingContent = { 
                        Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it }) 
                    }
                )
                
                ListItem(
                    headlineContent = { Text("About AnkYudh") },
                    leadingContent = { Icon(Icons.Default.Info, contentDescription = null) },
                    trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
                    modifier = Modifier.clickable { }
                )
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Text(label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
