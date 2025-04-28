package com.umesh.composetravel.model

data class FavoriteItem(
    val id: String,
    val title: String,
    val location: String,
    val imageUrl: String? = null,
    val imageUri: String? = null,
    val isFavorite: Boolean
)