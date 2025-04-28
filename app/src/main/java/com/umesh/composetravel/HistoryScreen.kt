package com.umesh.composetravel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {
    // Datos de ejemplo para el historial
    val purchaseHistory = listOf(
        TravelPurchase(
            id = "1",
            destination = "Isla Chifron",
            date = Date(),
            amount = 120.50,
            status = "Completed"
        ),
        TravelPurchase(
            id = "2",
            destination = "Isla Chifron",
            date = Date(System.currentTimeMillis() - 86400000), // Ayer
            amount = 85.75,
            status = "Completed"
        ),
        TravelPurchase(
            id = "3",
            destination = "Isla Chifron",
            date = Date(System.currentTimeMillis() - 259200000), // Hace 3 días
            amount = 150.00,
            status = "Cancelled"
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Historial de Compras") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Tus reservas anteriores",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            if (purchaseHistory.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No tienes compras registradas")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(purchaseHistory) { purchase ->
                        PurchaseItem(purchase = purchase)
                    }
                }
            }
        }
    }
}

@Composable
fun PurchaseItem(purchase: TravelPurchase) {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val bgColor = when (purchase.status) {
        "Cancelled" -> Color(0xFFFFEBEE)
        else -> Color(0xFFE8F5E9)
    }
    val textColor = when (purchase.status) {
        "Cancelled" -> Color(0xFFC62828)
        else -> Color(0xFF2E7D32)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = purchase.destination,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "$${"%.2f".format(purchase.amount)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dateFormat.format(purchase.date),
                    color = Color.Gray
                )
                Text(
                    text = purchase.status,
                    color = textColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

data class TravelPurchase(
    val id: String,
    val destination: String,
    val date: Date,
    val amount: Double,
    val status: String // "Completed", "Cancelled", "Pending"
)