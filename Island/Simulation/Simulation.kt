package IslandMain.Simulation

import all.Config
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Simulation(private val island: Island) {
    private val executor = Executors.newScheduledThreadPool(2)

    // Запускаем симуляцию с тактами
    fun start() {
        executor.scheduleAtFixedRate({ tick() }, 0, Config.TICK_DURATION_SECONDS, TimeUnit.SECONDS)
    }

    // Такт симуляции
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
