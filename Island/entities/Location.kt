package IslandMain.entities

class Location {
    val animals = mutableListOf<Animal>()
    var plants: Int = (5..20).random()  // Количество растений в клетке
}