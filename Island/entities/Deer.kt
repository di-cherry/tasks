package IslandMain.entities
import IslandMain.Simulation.Island

class Deer : Herbivore(40, 3, 20, 10, "🦌") {
    override fun move(island: Island, x: Int, y: Int) = chooseDirectionAndMove(island, x, y)
    override fun reproduce(location: Location) = reproduceIfPossible(location) { Deer() }
    // Функция для передвижения с учетом скорости
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
        if (sameSpecies.size >= 2 && location.animals.size < maxPerCell && (0..100).random() < 30) {
            val baby = createAnimal()
            location.animals.add(baby)
            println("$emoji размножились! 👶 Теперь в клетке: ${location.animals.count { it::class == this::class }}")
        }
    }
}
