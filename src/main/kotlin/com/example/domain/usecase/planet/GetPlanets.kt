package com.example.domain.usecase.planet

import com.example.model.PlanetDomain
import com.example.repository.PlanetRepository

interface GetPlanets {
    suspend fun execute(): List<PlanetDomain>
}

class DefaultGetPlanets(
    private val repository: PlanetRepository
) : GetPlanets {
    override suspend fun execute(): List<PlanetDomain> {
        return repository.getPlanets()
    }
}