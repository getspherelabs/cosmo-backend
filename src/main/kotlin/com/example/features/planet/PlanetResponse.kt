package com.example.features.planet

import com.example.common.http.Response
import com.example.common.http.Status
import kotlinx.serialization.Serializable

@Serializable
data class PlanetDto(
    val id: String,
    val name: String,
    val description: String,
    val size: String,
    val distanceFromSun: String,
    val isPopular: Boolean,
    val createdTimestamp: Long,
    val updatedTimestamp: Long,
    val image: String
)


@Serializable
data class PlanetsResponse(
    override val status: Status,
    override val message: String,
    val planets: List<PlanetDto> = emptyList()
) : Response {
    companion object {
        fun onSuccess(planets: List<PlanetDto>) = PlanetsResponse(
            Status.Success,
            "Get planets successful",
            planets
        )

        fun onFailure(message: String) = PlanetsResponse(
            Status.Failed,
            message
        )
    }
}

@Serializable
data class PlanetIdResponse(
    override val status: Status,
    override val message: String,
    val id: String? = null
) : Response {

    companion object {
        fun onFailure(message: String): PlanetIdResponse {
            return PlanetIdResponse(
                Status.Failed,
                message
            )
        }

        fun onNotFound(message: String): PlanetIdResponse {
            return PlanetIdResponse(
                Status.NotFound,
                message
            )
        }

        fun onSuccess(id: String) = PlanetIdResponse(
            Status.Success,
            "Task was successful",
            id
        )
    }
}

@Serializable
data class PlanetResponse(
    override val status: Status,
    override val message: String,
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val size: String = "",
    val distanceFromSun: String = "",
    val isPopular: Boolean = false,
    val createdTimestamp: Long = 0L,
    val updatedTimestamp: Long = 0L,
    val image: String = ""
) : Response {
    companion object {
        fun onFailure(message: String): PlanetResponse {
            return PlanetResponse(
                Status.Failed,
                message
            )
        }

        fun onNotFound(message: String): PlanetResponse {
            return PlanetResponse(
                Status.NotFound,
                message
            )
        }

        fun onSuccess(planet: PlanetDto) = PlanetResponse(
            Status.Success,
            "Task was successful",
            id = planet.id,
            name = planet.name,
            description = planet.description,
            size = planet.size,
            distanceFromSun = planet.distanceFromSun,
            createdTimestamp = planet.createdTimestamp,
            updatedTimestamp = planet.updatedTimestamp
        )
    }
}