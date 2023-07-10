package com.example.domain.usecase.star

import com.example.repository.StarRepository

interface InsertStar {
    suspend fun execute(name: String, description: String, distanceFromSun: String, size: String): String
}

class DefaultInsertStart(
    private val repository: StarRepository
): InsertStar {
    override suspend fun execute(name: String, description: String, distanceFromSun: String, size: String): String {
        return repository.insert(name, description, distanceFromSun, size)
    }
}