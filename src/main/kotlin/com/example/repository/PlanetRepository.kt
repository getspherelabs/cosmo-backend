package com.example.repository

import com.example.dao.PlanetDao
import com.example.model.PlanetDomain

interface PlanetRepository {
    suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String
    suspend fun getPlanets(): List<PlanetDomain>
    suspend fun removeById(id: String): Boolean
}

class DefaultPlanetRepository(
    private val dao: PlanetDao
) : PlanetRepository {
    override suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String {
        return dao.insert(name, description, size, distanceFromSun)
    }

    override suspend fun getPlanets(): List<PlanetDomain> {
        return dao.getPlanets()
    }

    override suspend fun removeById(id: String): Boolean {
        return dao.removeById(id)
    }
}