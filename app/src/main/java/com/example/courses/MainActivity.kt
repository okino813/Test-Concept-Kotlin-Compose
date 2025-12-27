package com.example.courses

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.courses.ui.theme.CoursesTheme
import androidx.navigation.compose.*
import com.example.courses.screens.*
import com.example.courses.Constants.BottomNavItems

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme(
                dynamicColor = false,
                darkTheme = false
            ){
                Surface(color = Color.White) {
                    MyApp()
//                    ScreenMain()
                }
            }
        }
    }
}

@Composable
fun ScreenMain(
    navController: NavHostController,
    padding: PaddingValues,
    counter: Int,
    onIncrement: () -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(padding)
    ) {

        composable(Constants.BottomNavItems[0].route) {
            Home(counter = counter, onIncrement = onIncrement)
        }

        composable(Constants.BottomNavItems[1].route) {
            Constants.BottomNavItems[1].composable()
        }

        composable(Constants.BottomNavItems[2].route) {
            Constants.BottomNavItems[2].composable()
        }

        composable(Constants.BottomNavItems[3].route) {
            Constants.BottomNavItems[3].composable()
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {

    NavigationBar(
        // set background color
        containerColor = Color(0xFF0F9D58)) {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        Constants.BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            NavigationBarItem(

                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route)
                },

                // Icon of navItem
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },

                // label
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false,

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White, // Icon color when selected
                    unselectedIconColor = Color.White, // Icon color when not selected
                    selectedTextColor = Color.White, // Label color when selected
                    indicatorColor = Color(0xFF195334) // Highlight color for selected item
                )
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(){
    val context = LocalContext.current
    val navController = rememberNavController()
    var counter by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Petit test de navigation") },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondary)
            )
        },
        // floating button
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    Toast.makeText(context, "Button cliqué", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },

        // main surface

        content = { padding ->
            // Nav host: where screens are placed
            ScreenMain(
                navController = navController,
                padding = padding,
                counter = counter,
                onIncrement = { counter++ }
                )
        },

        // bottom app bar
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )

}