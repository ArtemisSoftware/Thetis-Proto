package com.artemissoftware.thetisproto.models

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class SeasonSettings(
    val favorite: Seasons = Seasons.SPRING,
    val knownLocations: PersistentList<Location> = persistentListOf()
)

@Serializable
data class Location(
    val lat: Double,
    val lng: Double
)

enum class Seasons {
    SPRING, SUMMER, AUTUMN, WINTER
}