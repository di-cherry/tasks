//1. Основы LocalDate и LocalTime
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun main() {
    val currentDate = LocalDate.now()
    val currentTime = LocalTime.now()

    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
    println(currentDate.atTime(currentTime).format(formatter))
}

//2. Сравнение дат
//import java.time.LocalDate
//
//fun compareDates(date1: LocalDate, date2: LocalDate): String {
//    return when {
//        date1.isAfter(date2) -> "$date1 is after $date2"
//        date1.isBefore(date2) -> "$date1 is before $date2"
//        else -> "$date1 is equal to $date2"
//    }
//}
//
//fun main() {
//    val date1 = LocalDate.of(2025, 1, 1)
//    val date2 = LocalDate.of(2024, 1, 1)
//
//    println(compareDates(date1, date2))
//}

//3. Сколько дней до Нового года?
//import java.time.LocalDate
//import java.time.temporal.ChronoUnit
//
//fun daysUntilNewYear(): Long {
//    val currentDate = LocalDate.now()
//    val newYear = LocalDate.of(currentDate.year + 1, 1, 1)
//    return ChronoUnit.DAYS.between(currentDate, newYear)
//}
//
//fun main() {
//    println("Days until New Year: ${daysUntilNewYear()}")
//}

//4. Проверка високосного года
//fun isLeapYear(year: Int): Boolean {
//    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
//}
//
//fun main() {
//    val year = 2024
//    println("Is $year a leap year? ${isLeapYear(year)}")
//}

//5. Подсчет выходных за месяц
//import java.time.LocalDate
//import java.time.Month
//import java.time.temporal.TemporalAdjusters
//
//fun countWeekendsInMonth(year: Int, month: Month): Int {
//    var weekendCount = 0
//    val firstDayOfMonth = LocalDate.of(year, month, 1)
//    val lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth())
//
//    var currentDay = firstDayOfMonth
//    while (!currentDay.isAfter(lastDayOfMonth)) {
//        if (currentDay.dayOfWeek.value == 6 || currentDay.dayOfWeek.value == 7) {
//            weekendCount++
//        }
//        currentDay = currentDay.plusDays(1)
//    }
//
//    return weekendCount
//}
//
//fun main() {
//    val year = 2025
//    val month = Month.JANUARY
//    println("Weekends in $month $year: ${countWeekendsInMonth(year, month)}")
//}

//6. Расчет времени выполнения метода
//fun measureExecutionTime() {
//    val startTime = System.nanoTime()
//    // Example task: loop 1 million times
//    for (i in 1..1_000_000) {}
//    val endTime = System.nanoTime()
//    println("Execution time: ${endTime - startTime} nanoseconds")
//}
//
//fun main() {
//    measureExecutionTime()
//}

//7. Форматирование и парсинг даты
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter
//
//fun parseAndAddDays(dateString: String): String {
//    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//    val date = LocalDate.parse(dateString, formatter)
//    val newDate = date.plusDays(10)
//    val outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
//    return newDate.format(outputFormatter)
//}
//
//fun main() {
//    val dateString = "29-01-2025"
//    println("New date: ${parseAndAddDays(dateString)}")
//}

//8. Конвертация между часовыми поясами
//import java.time.Instant
//import java.time.ZoneId
//import java.time.ZonedDateTime
//
//fun convertTimezone(utcDateTime: String, targetZone: String): String {
//    val utcZonedDateTime = ZonedDateTime.parse(utcDateTime).withZoneSameInstant(ZoneId.of("UTC"))
//    val targetZoneDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.of(targetZone))
//    return targetZoneDateTime.toString()
//}
//
//fun main() {
//    val utcDateTime = "2025-01-29T12:00:00Z" // UTC
//    println("Moscow time: ${convertTimezone(utcDateTime, "Europe/Moscow")}")
//}

//9. Вычисление возраста по дате рождения
//import java.time.LocalDate
//import java.time.Period
//
//fun calculateAge(birthDate: LocalDate): Int {
//    val currentDate = LocalDate.now()
//    val period = Period.between(birthDate, currentDate)
//    return period.years
//}
//
//fun main() {
//    val birthDate = LocalDate.of(1990, 1, 29)
//    println("Age: ${calculateAge(birthDate)} years")
//}

//10. Создание календаря на месяц
//import java.time.LocalDate
//import java.time.Month
//import java.time.temporal.TemporalAdjusters
//
//fun generateCalendar(year: Int, month: Month) {
//    val firstDayOfMonth = LocalDate.of(year, month, 1)
//    val lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth())
//
//    var currentDay = firstDayOfMonth
//    while (!currentDay.isAfter(lastDayOfMonth)) {
//        val isWeekend = currentDay.dayOfWeek.value == 6 || currentDay.dayOfWeek.value == 7
//        println("${currentDay.dayOfMonth} ${if (isWeekend) "Weekend" else "Weekday"}")
//        currentDay = currentDay.plusDays(1)
//    }
//}
//
//fun main() {
//    val year = 2025
//    val month = Month.JANUARY
//    generateCalendar(year, month)
//}

//11. Генерация случайной даты в диапазоне
//import java.time.LocalDate
//import kotlin.random.Random
//
//fun generateRandomDate(startDate: LocalDate, endDate: LocalDate): LocalDate {
//    val daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate)
//    val randomDays = Random.nextLong(daysBetween)
//    return startDate.plusDays(randomDays)
//}
//
//fun main() {
//    val startDate = LocalDate.of(2023, 1, 1)
//    val endDate = LocalDate.of(2025, 12, 31)
//    println("Random date: ${generateRandomDate(startDate, endDate)}")
//}

//12. Расчет времени до заданной даты
//import java.time.LocalDateTime
//import java.time.Duration
//
//fun timeUntilEvent(eventDateTime: LocalDateTime): String {
//    val currentDateTime = LocalDateTime.now()
//    val duration = Duration.between(currentDateTime, eventDateTime)
//    val hours = duration.toHours()
//    val minutes = duration.toMinutes() % 60
//    val seconds = duration.seconds % 60
//    return "$hours hours, $minutes minutes, $seconds seconds"
//}
//
//fun main() {
//    val eventDateTime = LocalDateTime.of(2025, 1, 30, 12, 0)
//    println("Time until event: ${timeUntilEvent(eventDateTime)}")
//}

//13. Вычисление количества рабочих часов
//import java.time.LocalDateTime
//import java.time.temporal.ChronoUnit
//
//fun workingHours(start: LocalDateTime, end: LocalDateTime): Long {
//    val startOfDay = start.toLocalDate().atStartOfDay()
//    val endOfDay = end.toLocalDate().atTime(17, 0)  // Assuming working day ends at 5:00 PM
//    return ChronoUnit.HOURS.between(startOfDay, endOfDay)
//}
//
//fun main() {
//    val start = LocalDateTime.of(2025, 1, 29, 9, 0)  // 9 AM
//    val end = LocalDateTime.of(2025, 1, 29, 17, 0)   // 5 PM
//    println("Working hours: ${workingHours(start, end)}")
//}

//14. Конвертация даты в строку с учетом локали
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter
//import java.util.*
//
//fun formatDateWithLocale(date: LocalDate, locale: Locale): String {
//    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", locale)
//    return date.format(formatter)
//}
//
//fun main() {
//    val date = LocalDate.of(2025, 1, 29)
//    val locale = Locale("ru")
//    println("Formatted date: ${formatDateWithLocale(date, locale)}")
//}

//15. Определение дня недели по дате
//import java.time.LocalDate
//import java.time.format.TextStyle
//import java.util.*
//
//fun getWeekdayName(date: LocalDate): String {
//    return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ru"))
//}
//
//fun main() {
//    val date = LocalDate.of(2025, 1, 29)
//    println("Weekday: ${getWeekdayName(date)}")
//}