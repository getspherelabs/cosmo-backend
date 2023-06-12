package com.example.dao

import com.example.common.dbQuery
import com.example.entity.StarEntity
import com.example.entity.asStar
import com.example.model.Star
import java.util.*

interface StarDao {
    suspend fun insert(name: String, description: String, distanceFromSun: String, size: String): String
    suspend fun getStars(): List<Star>
    suspend fun removeById(id: String): Boolean
}

class DefaultStarDao : StarDao {
    override suspend fun insert(name: String, description: String, distanceFromSun: String, size: String): String {
        return dbQuery {
            StarEntity.new {
                this.name = name
                this.distanceFromSun = distanceFromSun
                this.size = size
                this.description = description
            }.id.value.toString()
        }
    }

    override suspend fun getStars(): List<Star> {
        return dbQuery {
            StarEntity.all().map { it.asStar() }
        }
    }

    override suspend fun removeById(id: String): Boolean {
        return dbQuery {
            val currentStart = StarEntity.findById(UUID.fromString(id))

            currentStart?.run {
                delete()
                return@dbQuery true
            }
            return@dbQuery false
        }
    }
}