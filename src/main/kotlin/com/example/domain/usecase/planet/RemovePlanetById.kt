package com.example.domain.usecase.planet

import com.example.repository.PlanetRepository

interface RemovePlanetById {
    suspend fun execute(id: String): Boolean
}

class DefaultRemovePlanetById(
    private val repository: PlanetRepository
) : RemovePlanetById {
    override suspend fun execute(id: String): Boolean {
        return repository.removeById(id)
    }
}