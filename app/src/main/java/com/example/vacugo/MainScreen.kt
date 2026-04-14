package com.example.vacugo
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    // Para saber qué ruta está activa y marcar el tab seleccionado
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    // Lista de items del bottom bar (más limpio)
    val items = listOf(
        Triple(Screen.Home, Icons.Default.Home, "Inicio"),
        Triple(Screen.Registro, Icons.Default.Person, "Registro"),
        Triple(Screen.Estadisticas, Icons.Default.BarChart, "Stats"),
        Triple(Screen.Profesional, Icons.Default.List, "Pro")
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { (screen, icon, label) ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Evita duplicados en el backstack
                                launchSingleTop = true
                                // Restaura estado al volver a una pestaña
                                restoreState = true
                                // Mantiene backstack limpio entre tabs
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Registro.route) { RegistroScreen() }
            composable(Screen.Estadisticas.route) { EstadisticasScreen() }
            composable(Screen.Profesional.route) { ProfesionalScreen() }
        }
    }
}
