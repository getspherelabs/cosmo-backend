package com.example.repository

import com.example.dao.StarDao
import com.example.model.StarDomain

interface  StarRepository {
    suspend fun insert(name: String, description: String, distanceFromSun: String, size: String): String
    suspend fun getStars(): List<StarDomain>
    suspend fun removeById(id: String): Boolean
}

class DefaultStarRepository (
    private val dao: StarDao
): StarRepository {
    override suspend fun insert(name: String, description: String, distanceFromSun: String, size: String): String {
        return dao.insert(name, description, distanceFromSun, size)
    }

    override suspend fun getStars(): List<StarDomain> {
        return dao.getStars()
    }

    override suspend fun removeById(id: String): Boolean {
        return dao.removeById(id)
    }
}