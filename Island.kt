import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.random.Random

// Остров
class Island(val width: Int, val height: Int) {
    val grid: Array<Array<Location>> = Array(width) { Array(height) { Location() } }

    fun printStats() {
        println("\n=== 🏝️ Остров: текущее состояние ===")
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
}

// Клетка острова
class Location {
    val animals = mutableListOf<Animal>()
    var plants: Int = Random.nextInt(5, 20) // Количество растений
}

// Базовый класс животного
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

    fun decreaseEnergy() {
        energy -= 5
        if (energy <= 0) println("$emoji погиб от голода! ☠️")
    }
}

// 🦊 Хищники
abstract class Predator(energy: Int, speed: Int, maxPerCell: Int, foodNeed: Int, emoji: String) :
    Animal(energy, speed, maxPerCell, foodNeed, emoji) {

    override fun eat(location: Location) {
        val prey = location.animals.filterIsInstance<Herbivore>().randomOrNull()
        if (prey != null && Random.nextInt(100) < 60) {
            location.animals.remove(prey)
            energy += foodNeed * 10
            println("$emoji съел ${prey.emoji}!")
        }
    }
}

// 🦌 Травоядные
abstract class Herbivore(energy: Int, speed: Int, maxPerCell: Int, foodNeed: Int, emoji: String) :
    Animal(energy, speed, maxPerCell, foodNeed, emoji) {

    override fun eat(location: Location) {
        if (location.plants >= foodNeed) {
            location.plants -= foodNeed
            energy += foodNeed * 5
            println("$emoji съел растение 🌿 ($foodNeed кг)")
        }
    }
}

// 🐺 Волк
class Wolf : Predator(50, 3, 30, 8, "🐺") {
    override fun move(island: Island, x: Int, y: Int) = chooseDirectionAndMove(island, x, y)
    override fun reproduce(location: Location) = reproduceIfPossible(location) { Wolf() }
}

// 🦌 Олень
class Deer : Herbivore(40, 3, 20, 10, "🦌") {
    override fun move(island: Island, x: Int, y: Int) = chooseDirectionAndMove(island, x, y)
    override fun reproduce(location: Location) = reproduceIfPossible(location) { Deer() }
}

// 🦆 Утка
class Duck : Herbivore(25, 4, 200, 1, "🦆") {
    override fun eat(location: Location) {
        val caterpillar = location.animals.filterIsInstance<Caterpillar>().randomOrNull()
        if (caterpillar != null) {
            location.animals.remove(caterpillar)
            energy += 5
            println("🦆 съела 🐛!")
        } else {
            super.eat(location)
        }
    }

    override fun move(island: Island, x: Int, y: Int) = chooseDirectionAndMove(island, x, y)
    override fun reproduce(location: Location) = reproduceIfPossible(location) { Duck() }
}

// 🐛 Гусеница
class Caterpillar : Herbivore(10, 1, 1000, 1, "🐛") {
    override fun move(island: Island, x: Int, y: Int) = chooseDirectionAndMove(island, x, y)
    override fun reproduce(location: Location) = reproduceIfPossible(location) { Caterpillar() }
}

// 🔄 Функция передвижения с учетом скорости
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

// Функция размножения
fun Animal.reproduceIfPossible(location: Location, createAnimal: () -> Animal) {
    val sameSpecies = location.animals.filter { it::class == this::class }

    if (sameSpecies.size >= 2 && location.animals.size < maxPerCell && Random.nextInt(100) < 30) {
        val baby = createAnimal()
        location.animals.add(baby)
        println("$emoji размножились! 👶 Теперь в клетке: ${location.animals.count { it::class == this::class }}")
    }
}

// **Симуляция**
class Simulation(private val island: Island) {
    private val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(3)

    fun start() {
        executor.scheduleAtFixedRate({ tick() }, 0, 2, TimeUnit.SECONDS)
    }

    private fun tick() {
        println("\n=== ⏳ Новый такт ===")

        for (x in island.grid.indices) {
            for (y in island.grid[x].indices) {
                val location = island.grid[x][y]
                location.animals.toList().forEach { animal ->
                    animal.move(island, x, y)
                    animal.eat(location)
                    animal.reproduce(location)
                    animal.decreaseEnergy()
                    if (animal.energy <= 0) location.animals.remove(animal)
                }
            }
        }

        island.printStats()
    }
}

// Запуск симуляции
fun main() {
    val island = Island(5, 5)

    island.grid[2][2].animals.add(Wolf())
    island.grid[3][3].animals.add(Wolf())
    island.grid[3][3].animals.add(Deer())
    island.grid[1][1].animals.add(Duck())
    island.grid[1][1].animals.add(Duck())
    island.grid[4][4].animals.add(Caterpillar())

    val simulation = Simulation(island)
    simulation.start()
}