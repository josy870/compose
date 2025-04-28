package com.umesh.composetravel

import android.net.Uri
import androidx.compose.ui.text.style.TextAlign
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.umesh.composetravel.model.FavoriteItem



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController) {
    // Estado para los favoritos
    var favorites by remember {
        mutableStateOf(
            listOf(
                FavoriteItem(
                    id = "1",
                    title = "Machu Picchu",
                    location = "Cusco, Perú",
                    imageUrl = "https://example.com/machupicchu.jpg",
                    isFavorite = true
                ),
                FavoriteItem(
                    id = "2",
                    title = "Torres del Paine",
                    location = "Patagonia, Chile",
                    imageUrl = "https://example.com/torresdelpaine.jpg",
                    isFavorite = true
                )
            )
        )
    }

    // Estado para la imagen seleccionada
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Lanzador para seleccionar imágenes
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            // Aquí podrías añadir la nueva imagen a favoritos
            val newFavorite = FavoriteItem(
                id = (favorites.size + 1).toString(),
                title = "Nuevo Favorito",
                location = "Ubicación personalizada",
                imageUri = it.toString(),
                isFavorite = true
            )
            favorites = favorites + newFavorite
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mis Favoritos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { imagePicker.launch("image/*") }) {
                        Icon(Icons.Default.Add, contentDescription = "Añadir foto")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { imagePicker.launch("image/*") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir favorito")
            }
        }
    ) { innerPadding ->
        if (favorites.isEmpty()) {
            EmptyFavoritesView(onAddClick = { imagePicker.launch("image/*") })
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFF5F5F5)),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(favorites, key = { it.id }) { favorite ->
                    FavoriteCard(
                        favorite = favorite,
                        onRemoveClick = {
                            favorites = favorites.filter { it.id != favorite.id }
                        },
                        onClick = { /* Navegar a detalle */ }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteCard(
    favorite: FavoriteItem,
    onRemoveClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box(modifier = Modifier.height(200.dp)) {
                val painter = if (favorite.imageUri != null) {
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(favorite.imageUri)
                            .build()
                    )
                } else {
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(favorite.imageUrl)
                            .build()
                    )
                }

                Image(
                    painter = painter,
                    contentDescription = favorite.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = onRemoveClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Quitar de favoritos",
                        tint = Color.Red
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = favorite.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = favorite.location,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun EmptyFavoritesView(onAddClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "Sin favoritos",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "No tienes favoritos aún",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Guarda tus lugares favoritos para acceder fácilmente",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
        Button(
            onClick = onAddClick,
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Añadir primer favorito")
        }
    }
}

// Modelo de datos
data class FavoriteItem(
    val id: String,
    val title: String,
    val location: String,
    val imageUrl: String? = null,
    val imageUri: String? = null, // Para imágenes locales
    val isFavorite: Boolean
)