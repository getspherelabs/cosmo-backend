package com.example.controller

import com.example.dao.PlanetDao
import com.example.model.Planet

interface PlanetController {
    suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String
    suspend fun getPlanets(): List<Planet>
    suspend fun removeById(id: String): Boolean
}

class DefaultPlanetController: PlanetController {

    override suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getPlanets(): List<Planet> {
        TODO("Not yet implemented")
    }

    override suspend fun removeById(id: String): Boolean {
        TODO("Not yet implemented")
    }
}