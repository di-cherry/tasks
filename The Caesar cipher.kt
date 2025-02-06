import java.io.File

// Функция для чтения текста из файла
fun readFile(filePath: String): String {
    val file = File(filePath)
    if (!file.exists()) {
        throw IllegalArgumentException("Файл не существует: $filePath")
    }
    return file.readText()
}

// Функция для записи текста в файл
fun writeFile(filePath: String, content: String) {
    val file = File(filePath)
    file.writeText(content)
}

// Функция для определения языка текста
fun detectLanguage(text: String): String {
    val russianLetters = "абвгдежзийклмнопрстуфхцчшщъыьэюя"
    val englishLetters = "abcdefghijklmnopqrstuvwxyz"

    var russianCount = 0
    var englishCount = 0

    text.forEach { char ->
        if (russianLetters.contains(char)) russianCount++
        if (englishLetters.contains(char)) englishCount++
    }

    return when {
        russianCount > englishCount -> "RU"
        englishCount > russianCount -> "EN"
        else -> "UNKNOWN"
    }
}

// Функция для шифрования/расшифровки текста с сдвигом (Цезарь)
fun caesarShift(text: String, shift: Int, language: String): String {
    val russianAlphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя"
    val englishAlphabet = "abcdefghijklmnopqrstuvwxyz"

    val alphabet = when (language) {
        "RU" -> russianAlphabet
        "EN" -> englishAlphabet
        else -> throw IllegalArgumentException("Неизвестный язык")
    }

    val shiftMod = shift % alphabet.length // Сдвиг по длине алфавита

    return text.map { char ->
        if (char in alphabet) {
            val index = alphabet.indexOf(char)
            val newIndex = (index + shiftMod + alphabet.length) % alphabet.length
            alphabet[newIndex]
        } else {
            char
        }
    }.joinToString("")
}

// Функция для шифрования файла
fun encryptFile(inputFile: String, outputFile: String, shift: Int) {
    val text = readFile(inputFile)
    val language = detectLanguage(text) // Определяем язык текста
    val encryptedText = caesarShift(text, shift, language)
    writeFile(outputFile, encryptedText)
}

// Функция для расшифровки файла
fun decryptFile(inputFile: String, outputFile: String, shift: Int) {
    val text = readFile(inputFile)
    val language = detectLanguage(text) // Определяем язык текста
    val decryptedText = caesarShift(text, -shift, language) // Расшифровка с отрицательным сдвигом
    writeFile(outputFile, decryptedText)
}

// Функция для брутфорс-расшифровки файла
fun bruteForceDecrypt(inputFile: String, outputFile: String) {
    val encryptedText = readFile(inputFile)
    val language = detectLanguage(encryptedText) // Определяем язык текста

    for (shift in 1 until 33) { // Пробуем все возможные сдвиги
        val decryptedText = caesarShift(encryptedText, -shift, language)
        println("Попытка с сдвигом $shift: $decryptedText")
        // Тут можно добавить проверку на осмысленность текста
    }
}

// Функция для статистического анализа, основанного на частоте букв
fun decryptTextStatisticalAnalysis(encryptedFilePath: String, decryptedFilePath: String) {
    try {
        val encryptedText = File(encryptedFilePath).readText()
        val decryptedText = findBestStatisticalDecryption(encryptedText)

        if (decryptedText != null) {
            File(decryptedFilePath).writeText(decryptedText.second)
        } else {
            println("Не удалось расшифровать текст, проверьте входные данные")
        }
    } catch (e: Exception) {
        println("Ошибка при статистическом анализе: ${e.message}")
    }
}

// Функция для нахождения лучшего расшифрованного текста на основе статистического анализа
fun findBestStatisticalDecryption(encryptedText: String): Pair<Int, String>? {
    val russianFrequency = mapOf('а' to 8.01, 'б' to 1.59, 'в' to 4.73, 'г' to 2.73, 'д' to 3.07, 'е' to 8.45, 'ё' to 0.30, 'ж' to 0.95, 'з' to 1.98, 'и' to 7.33, 'й' to 1.23, 'к' to 3.49, 'л' to 4.72, 'м' to 3.25, 'н' to 6.70, 'о' to 10.97, 'п' to 3.13, 'р' to 4.43, 'с' to 5.15, 'т' to 6.31, 'у' to 2.19, 'ф' to 0.24, 'х' to 0.87, 'ц' to 0.37, 'ч' to 1.03, 'ш' to 0.62, 'щ' to 0.32, 'ы' to 2.22, 'ь' to 1.45, 'э' to 0.24, 'ю' to 0.42, 'я' to 1.38)

    val englishFrequency = mapOf('a' to 8.17, 'b' to 1.49, 'c' to 2.78, 'd' to 4.25, 'e' to 12.70, 'f' to 2.23, 'g' to 2.02, 'h' to 6.09, 'i' to 6.97, 'j' to 0.15, 'k' to 0.77, 'l' to 4.03, 'm' to 2.41, 'n' to 6.75, 'o' to 7.51, 'p' to 1.93, 'q' to 0.10, 'r' to 5.99, 's' to 6.33, 't' to 9.06, 'u' to 2.76, 'v' to 0.98, 'w' to 2.36, 'x' to 0.15, 'y' to 1.97, 'z' to 0.07)

    var bestScore = 0.0
    var bestDecryption: Pair<Int, String>? = null

    // Пробуем все ключи шифрования (от 0 до длины алфавита)
    for (key in 0 until 33) { // Ограничиваем ключ от 0 до 32 для русского/английского алфавита
        val decryptedText = caesarShift(encryptedText, -key, detectLanguage(encryptedText)) // Расшифровка текста с отрицательным ключом
        val score = calculateFrequencyScore(decryptedText, if (detectLanguage(decryptedText) == "RU") russianFrequency else englishFrequency) // Сравнение с частотным анализом
        if (score > bestScore) {
            bestScore = score
            bestDecryption = key to decryptedText
        }
    }

    return bestDecryption
}

// Функция для подсчета схожести между частотами букв в тексте и в языке
fun calculateFrequencyScore(text: String, frequencyMap: Map<Char, Double>): Double {
    val textFrequency = mutableMapOf<Char, Double>()

    text.filter { it.isLetter() }.forEach { char ->
        val lowerChar = char.lowercaseChar()
        textFrequency[lowerChar] = textFrequency.getOrDefault(lowerChar, 0.0) + 1
    }

    // Преобразуем частоту символов в проценты
    val totalLetters = textFrequency.values.sum()
    textFrequency.forEach { (char, count) ->
        textFrequency[char] = (count / totalLetters) * 100
    }

    // Подсчитываем схожесть с реальной частотной таблицей
    var score = 0.0
    textFrequency.forEach { (char, frequency) ->
        score += (frequencyMap[char] ?: 0.0 - frequency).let { it * it }
    }

    return score
}

// Функция для проверки, существует ли файл по указанному пути
fun validateFilePath(filePath: String): Boolean {
    val file = File(filePath)
    return file.exists() && file.isFile
}

fun main() {
    // Пример использования
    println("Выберите действие:")
    println("1. Шифровать текст")
    println("2. Расшифровать текст (с известным ключом)")
    println("3. Расшифровать текст (brute force)")
    println("4. Расшифровка методом статистического анализа")
    println("5. Выход")

    when (readLine()) {
        "1" -> {
            println("Введите путь к файлу для шифрования:")
            val inputFile = readLine()!!
            println("Введите путь для сохранения зашифрованного текста:")
            val outputFile = readLine()!!
            println("Введите ключ (сдвиг):")
            val shift = readLine()!!.toInt()
            encryptFile(inputFile, outputFile, shift)
        }
        "2" -> {
            println("Введите путь к зашифрованному файлу:")
            val inputFile = readLine()!!
            println("Введите путь для сохранения расшифрованного текста:")
            val outputFile = readLine()!!
            println("Введите ключ (сдвиг):")
            val shift = readLine()!!.toInt()
            decryptFile(inputFile, outputFile, shift)
        }
        "3" -> {
            println("Введите путь к зашифрованному файлу:")
            val inputFile = readLine()!!
            println("Введите путь для сохранения расшифрованного текста:")
            val outputFile = readLine()!!
            bruteForceDecrypt(inputFile, outputFile)
        }
        "4" -> {
            println("Введите путь к зашифрованному файлу для статистического анализа:")
            val encryptedFilePath = readLine()!!
            println("Введите путь для сохранения расшифрованного текста:")
            val decryptedFilePath = readLine()!!
            decryptTextStatisticalAnalysis(encryptedFilePath, decryptedFilePath)
        }
        "5" -> println("Выход")
        else -> println("Неверный ввод")
    }
}
