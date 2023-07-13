package com.example.repository

import com.example.core.CosmoTest
import com.example.features.star.StarRepository
import io.github.serpro69.kfaker.faker
import org.junit.Test
import org.koin.test.inject
import java.util.*
import kotlin.test.assertEquals

class StarRepositoryTest: CosmoTest() {

    private val starRepository: StarRepository by inject()
    private val faker = faker { }

    @Test
    fun `should insert stars and verify counts`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val starCounts = starRepository.getStarCounts()

        assertEquals(2, starCounts)
    }

    @Test
    fun `should insert stars and get planets`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val stars = starRepository.getStars()

        assertEquals(desc, stars.first().description)
    }

    @Test
    fun `should insert planets and remove all`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        starRepository.removeAll()

        val starCounts = starRepository.getStarCounts()

        assertEquals(0, starCounts)
    }

    @Test
    fun `should insert stars and get by id`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val firstStar = starRepository.getStars().first()
        val newStar = starRepository.getStarById(firstStar.id)

        assertEquals(newStar, firstStar)
    }

    @Test
    fun `should insert star and check id if it is true`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )


        val firstStar = starRepository.getStars().first()
        val exists = starRepository.exists(firstStar.id)

        assertEquals(true, exists)
    }

    @Test
    fun `should insert star and check id if it is false`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val exists = starRepository.exists(UUID.randomUUID().toString())

        assertEquals(false, exists)
    }

    @Test
    fun `should insert planets and delete by id`() = withApp {
        val name = faker.space.star()
        val desc = faker.rickAndMorty.quotes()
        val isPopular = false
        val size = "100"
        val distanceFromSun = "1001km"

        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )
        starRepository.insert(
            name = name,
            description = desc,
            size = size,
            distanceFromSun = distanceFromSun,
            isPopular = isPopular
        )

        val firstStar = starRepository.getStars().first()
        val result = starRepository.deleteById(firstStar.id)
        val counts = starRepository.getStarCounts()

        assertEquals(true, result)
        assertEquals(1, counts)
    }
}