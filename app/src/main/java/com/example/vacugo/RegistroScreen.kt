package com.example.vacugo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class Registro(
    val nombre: String,
    val vacuna: String,
    val fecha: String
)

@Composable
fun RegistroScreen() {

    var nombre by rememberSaveable { mutableStateOf("") }
    var vacuna by rememberSaveable { mutableStateOf("") }
    var fecha by rememberSaveable { mutableStateOf("") }

    // Mejor para listas mutables en Compose
    val listaRegistros = remember { mutableStateListOf<Registro>() }

    // Snackbar para feedback
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Text(
                text = "Registro de Vacunación",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del paciente") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = vacuna,
                onValueChange = { vacuna = it },
                label = { Text("Tipo de vacuna") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (dd/mm/aaaa)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            val canSave = nombre.isNotBlank() && vacuna.isNotBlank() && fecha.isNotBlank()

            Button(
                onClick = {
                    if (!canSave) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Completa todos los campos antes de guardar.")
                        }
                        return@Button
                    }

                    listaRegistros.add(Registro(nombre.trim(), vacuna.trim(), fecha.trim()))

                    // limpiar campos
                    nombre = ""
                    vacuna = ""
                    fecha = ""
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = canSave
            ) {
                Text("Guardar")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Historial",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Ocupa el espacio restante para que no “apriete” la pantalla
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(
                    items = listaRegistros,
                    key = { it.nombre + it.vacuna + it.fecha }
                ) { registro ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text("👤 ${registro.nombre}")
                            Text("💉 ${registro.vacuna}")
                            Text("📅 ${registro.fecha}")
                        }
                    }
                }
            }
        }
    }
}
