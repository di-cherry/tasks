package IslandMain.entities

abstract class Predator(
    energy: Int,
    speed: Int,
    maxPerCell: Int,
    foodNeed: Int,
    emoji: String
) : Animal(energy, speed, maxPerCell, foodNeed, emoji) {

    override fun eat(location: Location) {
        val prey = location.animals.filter { it is Herbivore }.randomOrNull()

        if (prey != null) {
            val eatingChance = when (this) {
                is Wolf -> when (prey) {
                    is Rabbit -> 60
                    is Deer -> 80
                    is Goat -> 70
                    is Mouse -> 90
                    else -> 0
                }
                is Bear -> when (prey) {
                    is Fox -> 70
                    is Deer -> 80
                    is Rabbit -> 60
                    else -> 0
                }
                is Fox -> when (prey) {
                    is Mouse -> 90
                    is Rabbit -> 60
                    else -> 0
                }
                is Snake -> when (prey) {
                    is Mouse -> 80
                    else -> 0
                }
                is Eagle -> when (prey) {
                    is Mouse -> 80
                    else -> 0
                }
                else -> 0
            }

            // Если вероятность поедания больше случайного числа, съедаем добычу
            if (eatingChance > (0..100).random()) {
                location.animals.remove(prey)
                energy += foodNeed * 10
                println("$emoji съел ${prey.emoji}!")
            }
        }
    }
}



