package com.example.features.star

import com.example.data.dao.StarDao

interface StarRepository {
    suspend fun insert(
        name: String,
        description: String,
        size: String,
        distanceFromSun: String,
        isPopular: Boolean = false
    ): String

    suspend fun getStarCounts(): Long

    suspend fun removeAll()

    suspend fun getStars(): List<Star>

    suspend fun getStarById(id: String): Star

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

    suspend fun getPopularStars(): List<Star>
}

class DefaultStarRepository(
    private val dao: StarDao
) : StarRepository {
    override suspend fun insert(
        name: String,
        description: String,
        size: String,
        distanceFromSun: String,
        isPopular: Boolean
    ): String {
        return dao.insert(name, description, size, distanceFromSun, isPopular)
    }

    override suspend fun getStarCounts(): Long {
        return dao.getStarCounts()
    }

    override suspend fun removeAll() {
        dao.removeAll()
    }

    override suspend fun getStars(): List<Star> {
        return dao.getStars()
    }

    override suspend fun getStarById(id: String): Star {
        return dao.getStarById(id)
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

    override suspend fun getPopularStars(): List<Star> {
        return dao.getPopularStars()
    }
}