package com.example.controller

import com.example.dao.StarDao
import com.example.model.Star

interface StarController {
    suspend fun insert(name: String, description: String, distanceFromSun: String, size: String): String
    suspend fun getStars(): List<Star>
    suspend fun removeById(id: String): Boolean
}

class DefaultStartController(
    private val dao: StarDao
): StarController {
    override suspend fun insert(name: String, description: String, distanceFromSun: String, size: String): String {
        return dao.insert(name, description, distanceFromSun, size)
    }

    override suspend fun getStars(): List<Star> {
        return dao.getStars()
    }

    override suspend fun removeById(id: String): Boolean {
        return dao.removeById(id)
    }
}