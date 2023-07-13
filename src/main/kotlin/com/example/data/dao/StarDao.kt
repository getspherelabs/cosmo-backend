package com.example.data.dao

import com.example.common.extension.dbQuery
import com.example.data.entity.StarEntity
import com.example.data.table.StarTable
import com.example.features.star.Star
import com.example.features.star.asStar
import java.util.*

interface StarDao {
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

class DefaultStarDao : StarDao {
    override suspend fun insert(
        name: String,
        description: String,
        size: String,
        distanceFromSun: String,
        isPopular: Boolean
    ): String =
        dbQuery {
            StarEntity.new {
                this.name = name
                this.description = description
                this.size = size
                this.distanceFromSun = distanceFromSun
                this.isPopular = isPopular
            }.id.value.toString()
        }

    override suspend fun getStarCounts(): Long = dbQuery {
        StarEntity.all().count()
    }

    override suspend fun removeAll() = dbQuery {
        StarEntity.all().forEach { it.delete() }
    }

    override suspend fun getStars(): List<Star> = dbQuery {
        StarEntity.all().sortedBy { it.id }.map { it.asStar() }
    }

    override suspend fun getStarById(id: String): Star = dbQuery {
        checkNotNull(StarEntity.findById(UUID.fromString(id))?.asStar())
    }

    override suspend fun exists(id: String): Boolean = dbQuery {
        StarEntity.findById(UUID.fromString(id)) != null
    }

    override suspend fun deleteById(id: String): Boolean = dbQuery {
        val newStar = StarEntity.findById(UUID.fromString(id))

        newStar?.run {
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
        StarEntity[UUID.fromString(id)].apply {
            this.name = name
            this.description = description
            this.size = size
            this.distanceFromSun = distanceFromSun
            this.isPopular = isPopular
        }.id.value.toString()
    }

    override suspend fun getPopularStars(): List<Star> = dbQuery {
        StarEntity.find { StarTable.isPopular eq true }.toList().map { it.asStar() }
    }
}