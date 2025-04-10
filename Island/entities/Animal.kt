package IslandMain.entities
import IslandMain.Simulation.Island

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

    // Уменьшаем энергию и проверяем смерть
    fun decreaseEnergy() {
        energy -= 5
        if (energy <= 0) println("$emoji погиб от голода! ☠️")
    }
}