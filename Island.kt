import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.reflect.KClass

object Config {
    const val ISLAND_WIDTH = 5
    const val ISLAND_HEIGHT = 5
    const val TICK_DURATION_SECONDS = 2L

    val STARTING_ANIMALS = mapOf(
        Wolf::class to 2,
        Deer::class to 1,
        Duck::class to 2,
        Caterpillar::class to 1
    )

    val EATING_CHANCES = mapOf<KClass<out Animal>, Map<KClass<out Animal>, Int>>(
        Wolf::class to mapOf(
            Deer::class to 60,
            Duck::class to 40,
            Caterpillar::class to 0
        ),
        Duck::class to mapOf(
            Caterpillar::class to 90
        )
    )
}

// 🧬 Базовые классы животных
abstract class Animal(
    var energy: Int,
    val speed: Int,
    val maxPerCell: Int,
    val foodNeed: Int,
    val emoji: String
) {
    abstract fun move(island: Island, x: Int, y: Int)
    abstract fun eat(location: Location)
    abstract fun reproduce(location: Location)

    fun decreaseEnergy(): Boolean {
        energy -= 5
        if (energy <= 0) {
            println("$emoji погиб от голода ☠️")
            return true
        }
        return false
    }
}

abstract class Predator(
    energy: Int, speed: Int, maxPerCell: Int, foodNeed: Int, emoji: String
) : Animal(energy, speed, maxPerCell, foodNeed, emoji) {
    override fun eat(location: Location) {
        val preyList = location.animals.filter { it !is Predator }
        val prey = preyList.randomOrNull() ?: return
        val chance = Config.EATING_CHANCES[this::class]?.get(prey::class) ?: 0
        if (Random.nextInt(100) < chance) {
            location.animals.remove(prey)
            energy += foodNeed * 10
            println("$emoji съел ${prey.emoji}!")
        }
    }
}

abstract class Herbivore(
    energy: Int, speed: Int, maxPerCell: Int, foodNeed: Int, emoji: String
) : Animal(energy, speed, maxPerCell, foodNeed, emoji) {
    override fun eat(location: Location) {
        if (location.plants >= foodNeed) {
            location.plants -= foodNeed
            energy += foodNeed * 5
            println("$emoji съел растение 🌿 ($foodNeed кг)")
        }
    }
}

// 🐾 Конкретные животные
class Wolf : Predator(50, 3, 30, 8, "🐺") {
    override fun move(island: Island, x: Int, y: Int) = chooseDirectionAndMove(island, x, y)
    override fun reproduce(location: Location) = reproduceIfPossible(location) { Wolf() }
}

class Deer : Herbivore(40, 3, 20, 10, "🦌") {
    override fun move(island: Island, x: Int, y: Int) = chooseDirectionAndMove(island, x, y)
    override fun reproduce(location: Location) = reproduceIfPossible(location) { Deer() }
}

class Duck : Herbivore(25, 4, 200, 1, "🦆") {
    override fun eat(location: Location) {
        val prey = location.animals.filterIsInstance<Caterpillar>().randomOrNull()
        val chance = Config.EATING_CHANCES[this::class]?.get(Caterpillar::class) ?: 0
        if (prey != null && Random.nextInt(100) < chance) {
            location.animals.remove(prey)
            energy += 5
            println("🦆 съела 🐛!")
        } else {
            super.eat(location)
        }
    }

    override fun move(island: Island, x: Int, y: Int) = chooseDirectionAndMove(island, x, y)
    override fun reproduce(location: Location) = reproduceIfPossible(location) { Duck() }
}

class Caterpillar : Herbivore(10, 1, 1000, 1, "🐛") {
    override fun move(island: Island, x: Int, y: Int) = chooseDirectionAndMove(island, x, y)
    override fun reproduce(location: Location) = reproduceIfPossible(location) { Caterpillar() }
}

// 🗺️ Клетка острова
class Location {
    val animals = mutableListOf<Animal>()
    var plants: Int = Random.nextInt(5, 20)
}

// Общие функции
fun Animal.chooseDirectionAndMove(island: Island, x: Int, y: Int) {
    repeat(speed) {
        val dx = listOf(-1, 0, 1).random()
        val dy = listOf(-1, 0, 1).random()
        val newX = (x + dx).coerceIn(0, island.width - 1)
        val newY = (y + dy).coerceIn(0, island.height - 1)
        if (island.grid[newX][newY].animals.size < maxPerCell) {
            island.grid[newX][newY].animals.add(this)
            island.grid[x][y].animals.remove(this)
            println("$emoji переместился в ($newX, $newY) 🏃‍♂️")
            return
        }
    }
}

fun Animal.reproduceIfPossible(location: Location, create: () -> Animal) {
    val sameSpecies = location.animals.filter { it::class == this::class }
    if (sameSpecies.size >= 2 && location.animals.size < maxPerCell && Random.nextInt(100) < 30) {
        val baby = create()
        location.animals.add(baby)
        println("$emoji размножились! 👶 (${baby.emoji})")
    }
}

// 🏝️ Остров
class Island(val width: Int, val height: Int) {
    val grid: Array<Array<Location>> = Array(width) { Array(height) { Location() } }

    fun printStats() {
        println("\n=== 🏝️ Состояние острова ===")
        for (row in grid) {
            for (cell in row) {
                if (cell.animals.isNotEmpty()) {
                    print("[${cell.animals.joinToString("") { it.emoji }}] ")
                } else {
                    print("[  ] ")
                }
            }
            println()
        }
    }

    fun populate() {
        for ((clazz, count) in Config.STARTING_ANIMALS) {
            repeat(count) {
                val x = Random.nextInt(width)
                val y = Random.nextInt(height)
                val animal = when (clazz) {
                    Wolf::class -> Wolf()
                    Deer::class -> Deer()
                    Duck::class -> Duck()
                    Caterpillar::class -> Caterpillar()
                    else -> error("Неизвестный вид животного: $clazz")
                }
                grid[x][y].animals.add(animal)
            }
        }
    }
}

// 🔁 Симуляция
class Simulation(private val island: Island) {
    private val executor = Executors.newScheduledThreadPool(1)

    fun start() {
        executor.scheduleAtFixedRate({ tick() }, 0, Config.TICK_DURATION_SECONDS, TimeUnit.SECONDS)
    }

    private fun tick() {
        println("\n Новый цикл")
        var anyAlive = false

        for (x in island.grid.indices) {
            for (y in island.grid[x].indices) {
                val location = island.grid[x][y]
                val copy = location.animals.toList()
                for (animal in copy) {
                    animal.move(island, x, y)
                    animal.eat(location)
                    animal.reproduce(location)
                    val dead = animal.decreaseEnergy()
                    if (dead) location.animals.remove(animal)
                }
                if (location.animals.isNotEmpty()) anyAlive = true
            }
        }

        island.printStats()

        if (!anyAlive) {
            println("\n💀 Все животные вымерли. Симуляция остановлена.")
            executor.shutdown()
        }
    }
}

fun main() {
    val island = Island(Config.ISLAND_WIDTH, Config.ISLAND_HEIGHT)
    island.populate()
    val simulation = Simulation(island)
    simulation.start()
}
