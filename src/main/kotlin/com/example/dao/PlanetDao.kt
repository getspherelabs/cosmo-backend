package com.example.dao

import com.example.common.dbQuery
import com.example.entity.PlanetEntity
import com.example.entity.asPlanet
import com.example.model.PlanetDomain
import java.util.*

interface PlanetDao {
    suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String
    suspend fun getPlanets(): List<PlanetDomain>
    suspend fun removeById(id: String): Boolean
}

class DefaultPlanetDao : PlanetDao {
    override suspend fun insert(name: String, description: String, size: String, distanceFromSun: String): String = dbQuery {
        PlanetEntity.new {
            this.name = name
            this.description = description
            this.size = size
            this.distanceFromSun = distanceFromSun
        }.id.value.toString()
    }

    override suspend fun getPlanets(): List<PlanetDomain> = dbQuery {
        PlanetEntity.all().sortedBy { it.id }.map { it.asPlanet() }
    }

    override suspend fun removeById(id: String): Boolean = dbQuery {
        val currentPlant = PlanetEntity.findById(UUID.fromString(id))
        currentPlant?.run {
            delete()
            return@dbQuery true
        }
        return@dbQuery false
    }
}