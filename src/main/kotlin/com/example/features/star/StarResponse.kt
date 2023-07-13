package com.example.features.star

import com.example.features.planet.PlanetDto
import com.example.features.planet.PlanetsResponse
import com.example.common.http.Response
import com.example.common.http.Status
import kotlinx.serialization.Serializable

@Serializable
data class StarDto(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isPopular: Boolean,
    val createdTimestamp: Long,
    val updatedTimestamp: Long
)

data class StarsResponse(
    override val status: Status,
    override val message: String,
    val list: List<StarDto> = emptyList()
): Response {
    companion object {
        fun onSuccess(planets: List<PlanetDto>) = PlanetsResponse(
            Status.Success,
            "Get stars successful",
            planets
        )

        fun onFailure(message: String) = PlanetsResponse(
            Status.Failed,
            message
        )
    }
}
