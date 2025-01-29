// Задача 1
object Database {
    init {
        println("Создание подключения к базе данных...")
    }

    fun connect() {
        println("Подключение к базе данных установлено.")
    }
}

fun main() {
    val db1 = Database
    val db2 = Database

    db1.connect()
    db2.connect()

    println("db1 и db2 ссылаются на один и тот же объект: ${db1 === db2}")
}


// Задача 2
//object Logger {
//    private val logs = mutableListOf<String>()
//
//    fun log(message: String) {
//        logs.add(message)
//    }
//
//    fun printLogs() {
//        logs.forEach { println(it) }
//    }
//
//    fun getLogs(): List<String> {
//        return logs.toList() // Return a copy to prevent external modification
//    }
//}
//
//
//fun main() {
//    Logger.log("Начало программы")
//    Logger.log("Пользователь вошел в систему")
//    Logger.log("Выполнена операция")
//    Logger.log("Завершение работы")
//
//
//    println("Все логи:")
//    Logger.printLogs()
//
//
//    val logsCopy = Logger.getLogs()
//    println("\nЛоги полученные через getLogs:")
//    logsCopy.forEach{ println(it)}
//}


// Задача 3
//enum class OrderStatus {
//    NEW,
//    IN_PROGRESS,
//    DELIVERED,
//    CANCELLED
//}
//
//class Order(val orderId: Int, var status: OrderStatus = OrderStatus.NEW) {
//
//    fun updateStatus(newStatus: OrderStatus) {
//        if (status == OrderStatus.DELIVERED && newStatus == OrderStatus.CANCELLED) {
//            println("Нельзя отменить доставленный заказ.")
//            return
//        }
//        if(status == newStatus){
//            println("Статус заказа не был изменен, так как он уже находится в этом статусе.")
//            return
//        }
//
//        status = newStatus
//        println("Статус заказа $orderId изменен на: $status")
//    }
//
//    fun displayStatus() {
//        println("Заказ $orderId имеет статус: $status")
//    }
//}
//
//fun main() {
//    val order1 = Order(123)
//    order1.displayStatus()
//    order1.updateStatus(OrderStatus.IN_PROGRESS)
//    order1.displayStatus()
//    order1.updateStatus(OrderStatus.DELIVERED)
//    order1.displayStatus()
//
//    order1.updateStatus(OrderStatus.CANCELLED)
//    order1.displayStatus()
//
//    val order2 = Order(456, OrderStatus.IN_PROGRESS)
//    order2.displayStatus()
//    order2.updateStatus(OrderStatus.CANCELLED)
//    order2.displayStatus()
//    order2.updateStatus(OrderStatus.CANCELLED)
//    order2.displayStatus()
//
//}


// Задача 4
//enum class Season {
//    WINTER,
//    SPRING,
//    SUMMER,
//    AUTUMN
//}
//
//fun getSeasonName(season: Season): String {
//    return when (season) {
//        Season.WINTER -> "Зима"
//        Season.SPRING -> "Весна"
//        Season.SUMMER -> "Лето"
//        Season.AUTUMN -> "Осень"
//    }
//}
//
//fun main() {
//    val winterName = getSeasonName(Season.WINTER)
//    println("Season.WINTER на русском: $winterName")
//
//    val springName = getSeasonName(Season.SPRING)
//    println("Season.SPRING на русском: $springName")
//
//    val summerName = getSeasonName(Season.SUMMER)
//    println("Season.SUMMER на русском: $summerName")
//
//    val autumnName = getSeasonName(Season.AUTUMN)
//    println("Season.AUTUMN на русском: $autumnName")
//}