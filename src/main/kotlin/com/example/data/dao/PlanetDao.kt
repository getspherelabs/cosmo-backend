package com.example.data.dao

import com.example.common.dbQuery
import com.example.data.entity.PlanetEntity
import com.example.features.planet.Planet
import com.example.features.planet.asPlanet
import com.example.data.table.PlanetTable
import java.util.*

interface PlanetDao {
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

class DefaultPlanetDao : PlanetDao {
    override suspend fun insert(
        name: String,
        description: String,
        size: String,
        distanceFromSun: String,
        isPopular: Boolean
    ): String = dbQuery {
        PlanetEntity.new {
            this.name = name
            this.description = description
            this.size = size
            this.distanceFromSun = distanceFromSun
            this.isPopular = isPopular
        }.id.value.toString()
    }

    override suspend fun getPlanetCounts(): Long = dbQuery {
        PlanetEntity.all().count()
    }

    override suspend fun removeAll() = dbQuery {
        PlanetEntity.all().forEach { it.delete() }
    }

    override suspend fun getPlanets(): List<Planet> = dbQuery {
        PlanetEntity.all().sortedBy { it.id }.map { it.asPlanet() }
    }

    override suspend fun getPlanetById(id: String): Planet = dbQuery {
        checkNotNull(PlanetEntity.findById(UUID.fromString(id))?.asPlanet())
    }

    override suspend fun exists(id: String): Boolean = dbQuery {
        PlanetEntity.findById(UUID.fromString(id)) != null
    }

    override suspend fun deleteById(id: String): Boolean = dbQuery {
        val planet = PlanetEntity.findById(UUID.fromString(id))

        planet?.run {
            delete()
            return@dbQuery true
        }
        return@dbQuery false
    }

    override suspend fun update(
        id: String,
        name: String,
        description: String,
        size: String,
        distanceFromSun: String,
        isPopular: Boolean
    ): String = dbQuery {
        PlanetEntity[UUID.fromString(id)].apply {
            this.name = name
            this.description = description
            this.isPopular = isPopular
            this.size = size
            this.distanceFromSun = distanceFromSun
        }.id.value.toString()
    }

    override suspend fun getPopularPlanets(): List<Planet> = dbQuery {
        PlanetEntity.find { PlanetTable.isPopular eq true }.toList().map { it.asPlanet() }
    }
}