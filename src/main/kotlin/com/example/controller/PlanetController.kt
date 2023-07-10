package com.example.controller

import com.example.common.BadRequestException
import com.example.dao.PlanetDao
import com.example.model.PlanetDomain

interface PlanetController {
    suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String
    suspend fun getPlanets(): List<PlanetDomain>
    suspend fun removeById(id: String): Boolean
}

class DefaultPlanetController(
    private val dao: PlanetDao
) : PlanetController {
    override suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String {
        return dao.insert(name, description, size, distanceFromSun)
    }

    override suspend fun getPlanets(): List<PlanetDomain> {
        return dao.getPlanets()
    }

    override suspend fun removeById(id: String): Boolean {
        return dao.removeById(id)
    }

    private fun validatePlanetOrThrow(
        title: String,
        description: String
    ) {
        val message = when {
            (title.isBlank()) or description.isBlank() -> {
                "Title and description must not be blank."
            }
            (title.length !in (4..20)) -> {
                "Title should be of minimum 4 and maximum 20 character in length."
            }
            else -> return
        }

        throw  BadRequestException(message)
    }
}