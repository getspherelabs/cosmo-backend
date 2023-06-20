package com.example.controller

import com.example.dao.PlanetDao
import com.example.model.Planet

interface PlanetController {
    suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String
    suspend fun getPlanets(): List<Planet>
    suspend fun removeById(id: String): Boolean
}

class DefaultPlanetController(
    private val dao: PlanetDao
) : PlanetController {
    override suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String {
        return dao.insert(name, description, size, distanceFromSun)
    }

    override suspend fun getPlanets(): List<Planet> {
        return dao.getPlanets()
    }

    override suspend fun removeById(id: String): Boolean {
        return dao.removeById(id)
    }
}