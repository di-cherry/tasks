package IslandMain.entities

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