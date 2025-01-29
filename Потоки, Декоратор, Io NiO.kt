//Задание 1: Работа с потоками ввода-вывода
import java.io.*

fun main() {
    val inputFile = "D:/IntelliJ IDEA Community Edition 2024.3.2/Проекты idea/tasks1/src/input.txt"
    val outputFile = "D:/IntelliJ IDEA Community Edition 2024.3.2/Проекты idea/tasks1/src/output.txt"

    try {
        processFile(inputFile, outputFile)
        println("Файл успешно обработан и записан в '$outputFile'")
    } catch (e: IOException) {
        println("Произошла ошибка при работе с файлами: ${e.message}")
    } catch (e: FileNotFoundException) {
        println("Файл '$inputFile' не найден: ${e.message}")
    }
}

fun processFile(inputFile: String, outputFile: String) {
    BufferedReader(FileReader(inputFile)).use { reader ->
        BufferedWriter(FileWriter(outputFile)).use { writer ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val upperCaseLine = line?.uppercase() ?: ""
                writer.write(upperCaseLine)
                writer.newLine()
            }
        }
    }
}

//Задание 2: Реализация паттерна Декоратор
//interface TextProcessor {
//    fun process(text: String): String
//}
//
//class SimpleTextProcessor : TextProcessor {
//    override fun process(text: String) = text
//}
//
//class UpperCaseDecorator(private val processor: TextProcessor) : TextProcessor {
//    override fun process(text: String): String {
//        return processor.process(text).uppercase()
//    }
//}
//
//class TrimDecorator(private val processor: TextProcessor) : TextProcessor {
//    override fun process(text: String): String {
//        return processor.process(text).trim()
//    }
//}
//
//class ReplaceDecorator(private val processor: TextProcessor) : TextProcessor {
//    override fun process(text: String): String {
//        return processor.process(text).replace(" ", "_")
//    }
//}
//
//fun main() {
//    val processor = ReplaceDecorator(
//        UpperCaseDecorator(
//            TrimDecorator(SimpleTextProcessor())
//        )
//    )
//
//    val result = processor.process(" Hello world ")
//    println(result)
//}

//Задание 3: Сравнение производительности IO и NIO
//import java.io.*
//import java.nio.file.*
//import java.nio.channels.FileChannel
//import java.nio.ByteBuffer
//
//fun readWithIO(inputFile: String, outputFile: String) {
//    val startTime = System.currentTimeMillis()
//    try {
//        BufferedReader(FileReader(inputFile)).use { reader ->
//            BufferedWriter(FileWriter(outputFile)).use { writer ->
//                var line: String?
//                while (reader.readLine().also { line = it } != null) {
//                    writer.write(line + "\n")
//                }
//            }
//        }
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
//    val endTime = System.currentTimeMillis()
//    println("IO время: ${endTime - startTime} ms")
//}
//
//fun readWithNIO(inputFile: String, outputFile: String) {
//    val startTime = System.currentTimeMillis()
//    try {
//        val inputChannel = FileChannel.open(Paths.get(inputFile), StandardOpenOption.READ)
//        val outputChannel = FileChannel.open(Paths.get(outputFile), StandardOpenOption.CREATE, StandardOpenOption.WRITE)
//
//        val buffer = ByteBuffer.allocate(1024)
//        while (inputChannel.read(buffer) > 0) {
//            buffer.flip()
//            outputChannel.write(buffer)
//            buffer.clear()
//        }
//        inputChannel.close()
//        outputChannel.close()
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
//    val endTime = System.currentTimeMillis()
//    println("NIO время: ${endTime - startTime} ms")
//}
//
//fun main() {
//    val inputFile = "input.txt"
//    val outputFileIO = "output_io.txt"
//    val outputFileNIO = "output_nio.txt"
//
//    readWithIO(inputFile, outputFileIO)
//    readWithNIO(inputFile, outputFileNIO)
//}

//Задание 4: Программа с использованием Java NIO
//import java.io.FileInputStream
//import java.io.FileOutputStream
//import java.nio.channels.FileChannel
//import java.nio.file.Paths
//import java.nio.file.StandardOpenOption
//import java.io.IOException
//
//fun copyFileUsingNIO(source: String, destination: String) {
//    try {
//        val srcChannel = FileInputStream(source).channel
//        val destChannel = FileOutputStream(destination).channel
//
//        srcChannel.transferTo(0, srcChannel.size(), destChannel)
//
//        srcChannel.close()
//        destChannel.close()
//
//        println("Файл успешно скопирован с помощью NIO")
//
//    } catch (e: IOException) {
//        println("Ошибка при копировании файла: ${e.message}")
//    }
//}
//
//fun main() {
//    val source = "large_file.txt"
//    val destination = "copied_file.txt"
//
//    copyFileUsingNIO(source, destination)
//}

//Задание 5: Асинхронное чтение файла с использованием NIO.2
//import java.nio.channels.AsynchronousFileChannel
//import java.nio.file.Paths
//import java.nio.ByteBuffer
//import java.nio.file.StandardOpenOption
//import java.nio.file.Files
//import java.nio.file.Path
//import java.nio.charset.StandardCharsets
//import java.nio.channels.CompletionHandler
//
//fun readFileAsync(filePath: String) {
//    val path: Path = Paths.get(filePath)
//
//    try {
//        val fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ)
//
//        val buffer = ByteBuffer.allocate(1024)
//
//        fileChannel.read(buffer, 0, buffer, object : CompletionHandler<Int, ByteBuffer> {
//            override fun completed(result: Int, attachment: ByteBuffer) {
//                println("Чтение файла завершено")
//                attachment.flip()
//                val content = StandardCharsets.UTF_8.decode(attachment).toString()
//                println(content)
//                attachment.clear()
//            }
//
//            override fun failed(exc: Throwable, attachment: ByteBuffer) {
//                println("Не удалось прочитать файл: ${exc.message}")
//            }
//        })
//    } catch (e: Exception) {
//        println("Ошибка при чтении файла: ${e.message}")
//    }
//}
//
//fun main() {
//    val filePath = "large_file.txt"
//
//    readFileAsync(filePath)
//    Thread.sleep(5000)
//}