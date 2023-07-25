package com.example.features.star


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
    val updatedTimestamp: Long,
    val image: String
)

@Serializable
data class StarsResponse(
    override val status: Status,
    override val message: String,
    val list: List<StarDto> = emptyList()
) : Response {
    companion object {
        fun onSuccess(stars: List<StarDto>) = StarsResponse(
            Status.Success,
            "Get stars successful",
            stars
        )

        fun onFailure(message: String) = StarsResponse(
            Status.Failed,
            message
        )
    }
}

@Serializable
data class StarIdResponse(
    override val status: Status,
    override val message: String,
    val id: String? = null
) : Response {

    companion object {
        fun onFailure(message: String): StarIdResponse {
            return StarIdResponse(
                Status.Failed,
                message
            )
        }

        fun onNotFound(message: String): StarIdResponse {
            return StarIdResponse(
                Status.NotFound,
                message
            )
        }

        fun onSuccess(id: String) = StarIdResponse(
            Status.Success,
            "Task was successful",
            id
        )
    }
}

@Serializable
data class StarResponse(
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
        fun onFailure(message: String): StarResponse {
            return StarResponse(
                Status.Failed,
                message
            )
        }

        fun onNotFound(message: String): StarResponse {
            return StarResponse(
                Status.NotFound,
                message
            )
        }

        fun onSuccess(star: StarDto) = StarResponse(
            Status.Success,
            "Task was successful",
            id = star.id,
            name = star.name,
            description = star.description,
            size = star.size,
            distanceFromSun = star.distanceFromSun,
            createdTimestamp = star.createdTimestamp,
            updatedTimestamp = star.updatedTimestamp
        )
    }
}