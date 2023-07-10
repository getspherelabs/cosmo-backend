package com.example.domain.usecase.star

import com.example.model.StarDomain
import com.example.repository.StarRepository

interface GetStars {
    suspend fun execute(): List<StarDomain>
}

class DefaultGetStars(
    private val repository: StarRepository
) : GetStars {
    override suspend fun execute(): List<StarDomain> {
        return repository.getStars()
    }
}