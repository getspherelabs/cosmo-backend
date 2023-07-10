package com.example.model.response

import com.example.model.Response
import com.example.model.Status
import kotlinx.serialization.Serializable

@Serializable
data class PlanetDto(
    val id: String,
    val name: String,
    val description: String,
    val createdTimestamp: Long,
    val distanceFromSun: String
)

@Serializable
data class PlanetsResponse(
    override val status: Status,
    override val message: String,
    val planets: List<PlanetDto> = emptyList()
) : Response {
    companion object {
        fun success(planets: List<PlanetDto>) = PlanetsResponse(
            Status.Success,
            "Get planets successful",
            planets
        )
    }
}

@Serializable
data class PlanetResponse(
    override val status: Status,
    override val message: String,
    val id: String? = null
) : Response {

    companion object {
        fun failed(message: String): PlanetResponse {
            return PlanetResponse(
                Status.Failed,
                message
            )
        }

        fun notFound(message: String): PlanetResponse {
            return PlanetResponse(
                Status.NotFound,
                message
            )
        }

        fun success(id: String) = PlanetResponse(
            Status.Success,
            "Get planets successful",
            id
        )
    }
}