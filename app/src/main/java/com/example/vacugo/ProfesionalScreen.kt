package com.example.vacugo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Paciente(
    val nombre: String,
    val vacuna: String,
    val fecha: String
)

@Composable
fun ProfesionalScreen() {

    // 🔹 Datos de ejemplo
    val pacientes = listOf(
        Paciente("Ana López", "COVID-19", "12/03/2026"),
        Paciente("Carlos Pérez", "Influenza", "10/03/2026"),
        Paciente("María García", "Hepatitis B", "08/03/2026")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Vista Profesional ️",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(pacientes) { paciente ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {

                        Text(
                            text = "👤 ${paciente.nombre}",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(" Vacuna: ${paciente.vacuna}")
                        Text(" Fecha: ${paciente.fecha}")
                    }
                }
            }
        }
    }
}