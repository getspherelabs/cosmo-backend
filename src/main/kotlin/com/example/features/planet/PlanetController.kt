package com.example.features.planet

import com.example.common.BadRequestException
import com.example.common.NotFoundException

interface PlanetController {
    suspend fun getPlanets(): PlanetsResponse
    suspend fun addPlanet(request: PlanetRequest): PlanetIdResponse

    suspend fun removeAll(): PlanetsResponse

    suspend fun deletePlanetById(planetId: String): PlanetIdResponse

    suspend fun getPopularPlanets(): PlanetsResponse

    suspend fun updatePlanet(
        planetId: String,
        request: PlanetRequest
    ): PlanetIdResponse

    suspend fun getPlanetById(
        planetId: String
    ): PlanetResponse
}

class DefaultPlanetController(
    private val repository: PlanetRepository
) : PlanetController {
    override suspend fun getPlanets(): PlanetsResponse {
        val planets = repository.getPlanets()

        return PlanetsResponse.onSuccess(planets.map { newPlanet ->
            PlanetDto(
                newPlanet.id,
                newPlanet.name,
                newPlanet.description,
                newPlanet.size,
                newPlanet.distanceFromSun,
                newPlanet.isPopular,
                newPlanet.createdTimestamp,
                newPlanet.updatedTimestamp
            )
        })
    }

    override suspend fun addPlanet(request: PlanetRequest): PlanetIdResponse {
        return try {
            val name = request.name.trim()
            val description = request.description.trim()
            val size = request.size.trim()
            val distanceFromSun = request.distanceFromSun.trim()
            val isPopular = request.isPopular

            validatePlanetOrThrow(name, description, size, distanceFromSun)

            val planetId = repository.insert(name, description, size, distanceFromSun, isPopular)

            PlanetIdResponse.onSuccess(planetId)
        } catch (e: BadRequestException) {
            PlanetIdResponse.onFailure(e.message)
        }
    }

    override suspend fun removeAll(): PlanetsResponse {
        return try {
            repository.removeAll()
            PlanetsResponse.onSuccess(emptyList())
        } catch (e: Exception) {
            val failureMsg = checkNotNull(e.message)
            PlanetsResponse.onFailure(failureMsg)
        }
    }

    override suspend fun deletePlanetById(planetId: String): PlanetIdResponse {
        return try {
            hasExist(planetId)

            if (repository.deleteById(planetId)) {
                PlanetIdResponse.onSuccess(planetId)
            } else {
                PlanetIdResponse.onFailure("Something went wrong!")
            }
        } catch (badRequestException: BadRequestException) {
            PlanetIdResponse.onFailure(badRequestException.message)
        } catch (nfe: NotFoundException) {
            PlanetIdResponse.onFailure(nfe.message)
        }
    }

    override suspend fun getPopularPlanets(): PlanetsResponse {
        return try {
            val result = repository.getPopularPlanets()
            PlanetsResponse.onSuccess(result.map { newPlanet ->
                PlanetDto(
                    id = newPlanet.id,
                    name = newPlanet.name,
                    description = newPlanet.description,
                    size = newPlanet.size,
                    distanceFromSun = newPlanet.distanceFromSun,
                    isPopular = newPlanet.isPopular,
                    createdTimestamp = newPlanet.createdTimestamp,
                    updatedTimestamp = newPlanet.updatedTimestamp
                )
            })
        } catch (e: Exception) {
            val failureMsg = checkNotNull(e.message)
            PlanetsResponse.onFailure(failureMsg)
        }
    }

    override suspend fun updatePlanet(planetId: String, request: PlanetRequest): PlanetIdResponse {
        return try {
            val name = request.name.trim()
            val description = request.description.trim()
            val size = request.size.trim()
            val distanceFromSun = request.distanceFromSun.trim()
            val isPopular = request.isPopular

            validatePlanetOrThrow(name, description, size, distanceFromSun)
            hasExist(id = planetId)

            val newPlanetId = repository.update(planetId, name, description, size, distanceFromSun, isPopular)
            PlanetIdResponse.onSuccess(newPlanetId)
        } catch (badRequestException: BadRequestException) {
            PlanetIdResponse.onFailure(badRequestException.message)
        } catch (notFoundException: NotFoundException) {
            PlanetIdResponse.onFailure(notFoundException.message)
        }
    }

    override suspend fun getPlanetById(planetId: String): PlanetResponse {
        return try {
            hasExist(planetId)

            val result = repository.getPlanetById(planetId)
            PlanetResponse.onSuccess(result.asDto())
        } catch (notFoundException: NotFoundException) {
            PlanetResponse.onNotFound(notFoundException.message)
        }
    }

    private suspend fun hasExist(id: String) {
        if (!repository.exists(id)) {
            throw NotFoundException(message = "Not exist planet")
        }
    }

    private fun validatePlanetOrThrow(
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