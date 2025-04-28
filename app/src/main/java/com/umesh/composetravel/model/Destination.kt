package com.umesh.composetravel.model

import com.umesh.composetravel.R


data class Destination(
    val destinationId: Int,
    val destinationName: String,
    val destinationCity: String,
    val destinationCountry: String,
    val destinationRating: String,
    val destinationDuration: String,
    val destinationTemp: String,
    val destinationDescription: String,
    val destinationImg: Int,
    val destinationTicketPrice: String,
    val isFavourite: Boolean = false
)

val destinations = listOf(
    Destination(
        destinationId = 1,
        destinationName = "Isla chifron",
        destinationCity = "Capachica",
        destinationCountry = "Puno-Perú",
        destinationRating = "4.8",
        destinationDuration = "8 hour",
        destinationTemp = "16 °C",
        destinationDescription = "Chifrón es una pintoresca península ubicada en el lago Titicaca, cerca de Puno (Perú), donde la naturaleza y la cultura andina se funden en un paisaje sereno. Conocida localmente como \"Chifrón\", esta zona destaca por sus playas de arena blanca, aguas cristalinas y miradores naturales que ofrecen vistas panorámicas del lago navegable más alto del mundo (3,812 msnm).",
        destinationImg = R.drawable.cardchifon,
        destinationTicketPrice = "$230",
        isFavourite = false
    ),
    Destination(
        destinationId = 2,
        destinationName = "Isla chifron",
        destinationCity = "Capachica",
        destinationCountry = "Puno-Perú",
        destinationRating = "4.8",
        destinationDuration = "8 hour",
        destinationTemp = "16 °C",
        destinationDescription = "Chifrón es una pintoresca península ubicada en el lago Titicaca, cerca de Puno (Perú), donde la naturaleza y la cultura andina se funden en un paisaje sereno. Conocida localmente como \"Chifrón\", esta zona destaca por sus playas de arena blanca, aguas cristalinas y miradores naturales que ofrecen vistas panorámicas del lago navegable más alto del mundo (3,812 msnm).",
        destinationImg = R.drawable.cardchifon,
        destinationTicketPrice = "$230",
        isFavourite = true
    ),
    Destination(
        destinationId = 3,
        destinationName = "Isla chifron",
        destinationCity = "Capachica",
        destinationCountry = "Puno-Perú",
        destinationRating = "4.8",
        destinationDuration = "8 hour",
        destinationTemp = "16 °C",
        destinationDescription = "Chifrón es una pintoresca península ubicada en el lago Titicaca, cerca de Puno (Perú), donde la naturaleza y la cultura andina se funden en un paisaje sereno. Conocida localmente como \"Chifrón\", esta zona destaca por sus playas de arena blanca, aguas cristalinas y miradores naturales que ofrecen vistas panorámicas del lago navegable más alto del mundo (3,812 msnm).",
        destinationImg = R.drawable.cardchifon,
        destinationTicketPrice = "$230",
        isFavourite = true
    ),
)