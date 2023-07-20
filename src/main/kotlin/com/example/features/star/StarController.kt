package com.example.features.star


import com.example.common.exception.BadRequestException
import com.example.common.exception.NotFoundException


interface StarController {
    suspend fun getStars(): StarsResponse
    suspend fun addStar(request: StarRequest): StarIdResponse

    suspend fun removeAll(): StarsResponse

    suspend fun deleteStarById(starId: String): StarIdResponse

    suspend fun getPopularStars(): StarsResponse

    suspend fun updateStar(
        starId: String,
        request: StarRequest
    ): StarIdResponse

    suspend fun getStarById(
        starId: String
    ): StarResponse

}

class DefaultStarController(
    private val repository: StarRepository
) : StarController {
    override suspend fun getStars(): StarsResponse {
        val newStars = repository.getStars()

        return StarsResponse.onSuccess(newStars.map { newStar ->
            StarDto(
                newStar.id,
                newStar.name,
                newStar.description,
                newStar.size,
                newStar.distanceFromSun,
                newStar.isPopular,
                newStar.createdTimestamp,
                newStar.updatedTimestamp,
                newStar.image
            )
        })
    }

    override suspend fun addStar(request: StarRequest): StarIdResponse {
        return try {
            val name = request.name.trim()
            val description = request.description.trim()
            val size = request.size.trim()
            val distanceFromSun = request.distanceFromSun.trim()
            val isPopular = request.isPopular

            validateStarOrThrow(name, description, size, distanceFromSun)

            val newArticleId = repository.insert(name, description, size, distanceFromSun, isPopular)

            StarIdResponse.onSuccess(newArticleId)
        } catch (e: BadRequestException) {
            StarIdResponse.onFailure(e.message)
        }
    }

    override suspend fun removeAll(): StarsResponse {
        return try {
            repository.removeAll()
            StarsResponse.onSuccess(emptyList())
        } catch (e: Exception) {
            val failureMsg = checkNotNull(e.message)
            StarsResponse.onFailure(failureMsg)
        }
    }

    override suspend fun deleteStarById(starId: String): StarIdResponse {
        return try {
            hasExist(starId)

            if (repository.deleteById(starId)) {
                StarIdResponse.onSuccess(starId)
            } else {
                StarIdResponse.onFailure("Something went wrong!")
            }
        } catch (badRequestException: BadRequestException) {
            StarIdResponse.onFailure(badRequestException.message)
        } catch (nfe: NotFoundException) {
            StarIdResponse.onFailure(nfe.message)
        }
    }

    override suspend fun getPopularStars(): StarsResponse {
        return try {
            val result = repository.getPopularStars()
            StarsResponse.onSuccess(result.map { newStar ->
                StarDto(
                    id = newStar.id,
                    name = newStar.name,
                    description = newStar.description,
                    size = newStar.size,
                    distanceFromSun = newStar.distanceFromSun,
                    isPopular = newStar.isPopular,
                    createdTimestamp = newStar.createdTimestamp,
                    updatedTimestamp = newStar.updatedTimestamp,
                    image = newStar.image
                )
            })
        } catch (e: Exception) {
            val failureMsg = checkNotNull(e.message)
            StarsResponse.onFailure(failureMsg)
        }
    }

    override suspend fun updateStar(starId: String, request: StarRequest): StarIdResponse {
        return try {
            val name = request.name.trim()
            val description = request.description.trim()
            val size = request.size.trim()
            val distanceFromSun = request.distanceFromSun.trim()
            val isPopular = request.isPopular

            validateStarOrThrow(name, description, size, distanceFromSun)
            hasExist(id = starId)

            val newStarId = repository.update(starId, name, description, size, distanceFromSun, isPopular)
            StarIdResponse.onSuccess(newStarId)
        } catch (badRequestException: BadRequestException) {
            StarIdResponse.onFailure(badRequestException.message)
        } catch (notFoundException: NotFoundException) {
            StarIdResponse.onFailure(notFoundException.message)
        }
    }

    override suspend fun getStarById(starId: String): StarResponse {
        return try {
            hasExist(starId)

            val result = repository.getStarById(starId)
            StarResponse.onSuccess(result.asDto())
        } catch (notFoundException: NotFoundException) {
            StarResponse.onNotFound(notFoundException.message)
        }
    }

    private suspend fun hasExist(id: String) {
        if (!repository.exists(id)) {
            throw NotFoundException(message = "Not exist planet")
        }
    }

    private fun validateStarOrThrow(
        name: String,
        description: String,
        size: String,
        distanceFromSun: String
    ) {
        val message = when {
            (name.isBlank() or description.isBlank()) -> "Name and description must not be blank."
            size.isBlank() -> "Size must not be blank."
            distanceFromSun.isBlank() -> "Distance from sun must not be blank."
            else -> return

        }
        throw BadRequestException(message)
    }

}