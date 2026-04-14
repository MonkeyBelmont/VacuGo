package com.example.vacugo
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Registro : Screen("registro")
    object Estadisticas : Screen("estadisticas")
    object Profesional : Screen("profesional")
}