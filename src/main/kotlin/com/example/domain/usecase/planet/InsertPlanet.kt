package com.example.domain.usecase.planet

import com.example.repository.PlanetRepository

interface InsertPlanet {
    suspend fun execute(name: String, description: String, size: String, distanceFromSun: String): String
}

class DefaultInsertPlanet(
    private val repository: PlanetRepository
) : InsertPlanet {
    override suspend fun execute(name: String, description: String, size: String, distanceFromSun: String): String {
        return repository.insert(name, description, size, distanceFromSun)
    }
}