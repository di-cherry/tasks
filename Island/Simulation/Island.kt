package IslandMain.Simulation

import IslandMain.Config
import IslandMain.entities.*

// Класс Island
class Island(val width: Int, val height: Int) {
    val grid: Array<Array<Location>> = Array(width) { Array(height) { Location() } }

    // Заполняем остров животными по заданным параметрам
    fun populate() {
        for ((animalClass, count) in Config.STARTING_ANIMALS) {
            repeat(count) {
                val animal: Animal = when (animalClass) {
                    Wolf::class.java -> Wolf()
                    Bear::class.java -> Bear()
                    Fox::class.java -> Fox()
                    Snake::class.java -> Snake()
                    Eagle::class.java -> Eagle()
                    Deer::class.java -> Deer()
                    Rabbit::class.java -> Rabbit()
                    Goat::class.java -> Goat()
                    Horse::class.java -> Horse()
                    Boar::class.java -> Boar()
                    Mouse::class.java -> Mouse()
                    Buffalo::class.java -> Buffalo()
                    Sheep::class.java -> Sheep()
                    Caterpillar::class.java -> Caterpillar()
                    else -> throw IllegalArgumentException("Unknown animal class")
                }
                val x = (0 until width).random()
                val y = (0 until height).random()
                grid[x][y].animals.add(animal)
            }
        }
    }

    // Метод для печати статистики острова
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