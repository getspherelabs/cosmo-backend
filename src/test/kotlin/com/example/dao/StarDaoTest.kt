package com.example.dao

import com.example.core.CosmoTest
import com.example.data.dao.StarDao
import io.github.serpro69.kfaker.faker
import org.junit.Test
import org.koin.test.inject
import java.util.UUID
import kotlin.test.assertEquals

class StarDaoTest : CosmoTest() {

    private val starDao: StarDao by inject()
    private val faker = faker { }

    @Test
    fun `should insert stars and verify counts`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val starCounts = starDao.getStarCounts()

        assertEquals(2, starCounts)
    }

    @Test
    fun `should insert stars and get planets`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val stars = starDao.getStars()

        assertEquals(desc, stars.first().description)
    }

    @Test
    fun `should insert planets and remove all`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        starDao.removeAll()

        val starCounts = starDao.getStarCounts()

        assertEquals(0, starCounts)
    }

    @Test
    fun `should insert stars and get by id`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val firstStar = starDao.getStars().first()
        val newStar = starDao.getStarById(firstStar.id)

        assertEquals(newStar, firstStar)
    }

    @Test
    fun `should insert star and check id if it is true`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )


        val firstStar = starDao.getStars().first()
        val exists = starDao.exists(firstStar.id)

        assertEquals(true, exists)
    }

    @Test
    fun `should insert star and check id if it is false`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val exists = starDao.exists(UUID.randomUUID().toString())

        assertEquals(false, exists)
    }

    @Test
    fun `should insert planets and delete by id`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starDao.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val firstStar = starDao.getStars().first()
        val result = starDao.deleteById(firstStar.id)
        val counts = starDao.getStarCounts()

        assertEquals(true, result)
        assertEquals(1, counts)
    }
}