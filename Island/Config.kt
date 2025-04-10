package IslandMain

import IslandMain.entities.*
import kotlin.reflect.KClass

object Config {
    // Размеры острова
    const val ISLAND_WIDTH = 10
    const val ISLAND_HEIGHT = 10
    const val TICK_DURATION_SECONDS = 2L

    // Начальное количество животных на острове
    val STARTING_ANIMALS = mapOf<Class<out Animal>, Int>(
        Wolf::class.java to 5,
        Bear::class.java to 3,
        Fox::class.java to 4,
        Snake::class.java to 3,
        Eagle::class.java to 2,
        Deer::class.java to 10,
        Rabbit::class.java to 12,
        Goat::class.java to 8,
        Horse::class.java to 5,
        Boar::class.java to 6,
        Mouse::class.java to 20,
        Buffalo::class.java to 3,
        Sheep::class.java to 10,
        Caterpillar::class.java to 30
    )

    // Вероятности поедания (с использованием Class<out Animal> вместо KClass)
    val EATING_CHANCES = mapOf<Class<out Animal>, Map<Class<out Animal>, Int>>(
        Wolf::class.java to mapOf(
            Rabbit::class.java to 60,
            Deer::class.java to 80,
            Goat::class.java to 70,
            Mouse::class.java to 90
        ),
        Bear::class.java to mapOf(
            Fox::class.java to 70,
            Deer::class.java to 80,
            Rabbit::class.java to 60
        ),
        Fox::class.java to mapOf(
            Mouse::class.java to 90,
            Rabbit::class.java to 60
        ),
        Snake::class.java to mapOf(
            Mouse::class.java to 80
        ),
        Eagle::class.java to mapOf(
            Mouse::class.java to 80
        )
    )
}
