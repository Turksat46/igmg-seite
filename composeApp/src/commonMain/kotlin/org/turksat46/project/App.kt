package org.turksat46.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

enum class Fragment {
    HOME, SETTINGS, PROFILE
}

data class Event(
    val name: String = "",
    val address: String = "",
    val date: String = "",
    val imageUrl: String = ""
)


@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(Fragment.HOME) }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("GT Württemberg",color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Open Drawer")
                    }
                },
                backgroundColor = Color.DarkGray,
            )
        },
        drawerContent = {
            DrawerContent { selected ->
                currentScreen = selected
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        },

    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            AnimatedVisibility(visible = currentScreen == Fragment.HOME, enter = fadeIn(), exit = fadeOut()) {
                HomeScreenContent()
            }
            AnimatedVisibility(visible = currentScreen == Fragment.SETTINGS, enter = fadeIn(), exit = fadeOut()) {
                SettingsScreenContent()
            }
            AnimatedVisibility(visible = currentScreen == Fragment.PROFILE, enter = fadeIn(), exit = fadeOut()) {
                ProfileScreenContent()
            }
        }
    }
}

@Composable
fun DrawerContent(onSelect: (Fragment) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF303030))) {
        Text("Navigation", fontSize = 20.sp, color = Color.White, modifier = Modifier.padding(16.dp))
        listOf(
            "Home" to Fragment.HOME,
            "Settings" to Fragment.SETTINGS,
            "Profile" to Fragment.PROFILE
        ).forEach { (title, fragment) ->
            Row(
                modifier = Modifier.fillMaxWidth().clickable { onSelect(fragment) }.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, color = Color.White)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(currentScreen: Fragment, onSelect: (Fragment) -> Unit) {
    BottomNavigation(backgroundColor = Color(0xFF424242)) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            selected = currentScreen == Fragment.HOME,
            onClick = { onSelect(Fragment.HOME) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            selected = currentScreen == Fragment.SETTINGS,
            onClick = { onSelect(Fragment.SETTINGS) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Profile") },
            selected = currentScreen == Fragment.PROFILE,
            onClick = { onSelect(Fragment.PROFILE) }
        )
    }
}

@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Bevorstehende Ereignisse", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                // RecyclerView Ersatz: Dummy-Text
                Text("Event 1\nEvent 2\nEvent 3")
            }
        }
        Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Ramazaninfos", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Imsak: 06:00\nAksam: 18:00")
            }
        }
        Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Folgt uns auf", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { /* Instagram-Link */ }) {
                    Text("Instagram")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { /* Facebook-Link */ }) {
                    Text("Facebook")
                }
            }
        }
    }
}

@Composable
fun SettingsScreenContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Settings Fragment Content", fontSize = 20.sp, color = Color.White)
    }
}

@Composable
fun ProfileScreenContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Profile Fragment Content", fontSize = 20.sp, color = Color.White)
    }
}
