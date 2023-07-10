package com.example.domain.usecase.star

import com.example.repository.StarRepository

interface RemoveStarById {
    suspend fun execute(id: String): Boolean
}

class DefaultRemoveStarById(
    private val repository: StarRepository
) : RemoveStarById {
    override suspend fun execute(id: String): Boolean {
        return repository.removeById(id)
    }
}