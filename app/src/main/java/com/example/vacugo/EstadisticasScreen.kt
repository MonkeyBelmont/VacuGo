package com.example.vacugo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EstadisticasScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .padding(16.dp)
    ) {

        Text(
            text = "Estadísticas 📊",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "COVID-19",
                value = "30",
                color = Color(0xFF1976D2),
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "Influenza",
                value = "20",
                color = Color(0xFF2196F3),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        StatCard(
            title = "Hepatitis B",
            value = "15",
            color = Color(0xFFFF9800),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Distribución",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        FakeBar(label = "COVID-19", progress = 0.7f, color = Color(0xFF1976D2))
        FakeBar(label = "Influenza", progress = 0.5f, color = Color(0xFF2196F3))
        FakeBar(label = "Hepatitis B", progress = 0.3f, color = Color(0xFFFF9800))
    }
}