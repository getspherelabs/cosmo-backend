package com.example.repository

import com.example.data.dao.PlanetDao
import com.example.data.model.Planet

interface PlanetRepository {
    suspend fun insert(
        name: String,
        description: String,
        size: String,
        distanceFromSun: String,
        isPopular: Boolean
    ): String

    suspend fun getPlanetCounts(): Long

    suspend fun removeAll()

    suspend fun getPlanets(): List<Planet>

    suspend fun getPlanetById(id: String): Planet

    suspend fun exists(id: String): Boolean

    suspend fun deleteById(id: String): Boolean

    suspend fun update(
        id: String,
        name: String,
        description: String,
        size: String,
        distanceFromSun: String,
        isPopular: Boolean
    ): String

    suspend fun getPopularPlanets(): List<Planet>
}

class DefaultPlanetRepository (
    private val dao: PlanetDao
) : PlanetRepository {
    override suspend fun insert(
        name: String,
        description: String,
        size: String,
        distanceFromSun: String,
        isPopular: Boolean
    ): String {
       return dao.insert(name, description, size, distanceFromSun, isPopular)
    }

    override suspend fun getPlanetCounts(): Long {
        return dao.getPlanetCounts()
    }

    override suspend fun removeAll() {
        dao.removeAll()
    }

    override suspend fun getPlanets(): List<Planet> {
        return dao.getPlanets()
    }

    override suspend fun getPlanetById(id: String): Planet {
        return dao.getPlanetById(id)
    }

    override suspend fun exists(id: String): Boolean {
        return dao.exists(id)
    }

    override suspend fun deleteById(id: String): Boolean {
        return dao.deleteById(id)
    }

    override suspend fun update(
        id: String,
        name: String,
        description: String,
        size: String,
        distanceFromSun: String,
        isPopular: Boolean
    ): String {
       return dao.update(id, name, description, size, distanceFromSun, isPopular)
    }

    override suspend fun getPopularPlanets(): List<Planet> {
        return dao.getPopularPlanets()
    }
}