import java.util.*
import java.util.concurrent.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    println("Задача 1: Общий счетчик")
    synchronizedCounterExample()

    println("\nЗадача 2: Генерация последовательности чисел")
    concurrentListExample()

    println("\nЗадача 3: Распределение задач с использованием пула потоков")
    threadPoolExample()

    println("\nЗадача 4: Симуляция работы банка")
    bankTransactionExample()

    println("\nЗадача 5: Барьер синхронизации")
    cyclicBarrierExample()

    println("\nЗадача 6: Ограниченный доступ к ресурсу")
    semaphoreExample()

    println("\nЗадача 7: Обработка результатов задач")
    callableFutureExample()

    println("\nЗадача 8: Симуляция производственной линии")
    blockingQueueExample()

    println("\nЗадача 9: Многопоточная сортировка")
    parallelSortExample()

    println("\nЗадача 10: Обед философов (*)")
    diningPhilosophersExample()

    println("\nЗадача 11: Расчёт матрицы в параллельных потоках")
    matrixMultiplicationExample()

    println("\nЗадача 12: Таймер с многопоточностью")
    timerWithThreadsExample()
}

// Задача 1: Общий счетчик
fun synchronizedCounterExample() {
    val counter = Counter()
    val threads = mutableListOf<Thread>()

    for (i in 1..5) {
        val thread = thread {
            for (j in 1..1000) {
                counter.increment()
            }
        }
        threads.add(thread)
    }

    threads.forEach { it.join() }

    println("Финальное значение счетчика: ${counter.count}")
}

class Counter {
    var count = 0
        private set

    private val lock = ReentrantLock()

    fun increment() {
        lock.lock()
        try {
            count++
        } finally {
            lock.unlock()
        }
    }
}

// Задача 2: Генерация последовательности чисел
fun concurrentListExample() {
    val numbers = ConcurrentLinkedQueue<Int>()
    val threads = mutableListOf<Thread>()

    for (i in 1..10) {
        val thread = thread {
            for (j in 1..100) {
                numbers.add(j)
            }
        }
        threads.add(thread)
    }

    threads.forEach { it.join() }

    println("Размер списка чисел: ${numbers.size}")
    println("Содержимое списка: ${numbers.toList()}")
}

// Задача 3: Распределение задач с использованием пула потоков
fun threadPoolExample() {
    val executor = Executors.newFixedThreadPool(4)

    for (i in 1..20) {
        executor.execute {
            println("Задача $i выполняется потоком: ${Thread.currentThread().name}")
        }
    }

    executor.shutdown()
    executor.awaitTermination(1, TimeUnit.MINUTES)
}

// Задача 4: Симуляция работы банка
fun bankTransactionExample() {
    val account1 = Account(1, 1000)
    val account2 = Account(2, 500)
    val account3 = Account(3, 2000)

    val accounts = listOf(account1, account2, account3)

    val threads = mutableListOf<Thread>()

    for (i in 1..10) {
        val thread = thread {
            for (j in 1..100) {
                // Randomly select accounts for transfer
                val fromAccount = accounts.random()
                var toAccount = accounts.random()
                while (toAccount == fromAccount) {
                    toAccount = accounts.random()
                }

                val amount = Random.nextInt(10, 50)
                transferMoney(fromAccount, toAccount, amount)
            }
        }
        threads.add(thread)
    }

    threads.forEach { it.join() }

    accounts.forEach { println("Account ${it.id} balance: ${it.balance}") }
}

data class Account(val id: Int, var balance: Int) {
    internal val lock = ReentrantLock()

    fun deposit(amount: Int) {
        lock.lock()
        try {
            balance += amount
        } finally {
            lock.unlock()
        }
    }

    fun withdraw(amount: Int) : Boolean {
        lock.lock()
        try {
            if (balance >= amount) {
                balance -= amount
                return true
            }
            return false
        } finally {
            lock.unlock()
        }
    }
}

fun transferMoney(fromAccount: Account, toAccount: Account, amount: Int) {
    if (fromAccount.lock.tryLock(10, TimeUnit.MILLISECONDS)) {
        try {
            if (toAccount.lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                try {
                    if (fromAccount.withdraw(amount)) {
                        toAccount.deposit(amount)
                        println("Transfer of $amount from Account ${fromAccount.id} to Account ${toAccount.id} successful.")
                    } else {
                        println("Insufficient funds in Account ${fromAccount.id} to transfer $amount.")
                    }
                } finally {
                    toAccount.lock.unlock()
                }
            } else {
                println("Could not acquire lock on Account ${toAccount.id} for transfer.")
            }
        } finally {
            fromAccount.lock.unlock()
        }
    } else {
        println("Could not acquire lock on Account ${fromAccount.id} for transfer.")
    }
}


// Задача 5: Барьер синхронизации
fun cyclicBarrierExample() {
    val parties = 5
    val barrier = CyclicBarrier(parties) {
        println("Все потоки достигли барьера. Запускаем следующую фазу!")
    }

    val threads = mutableListOf<Thread>()

    for (i in 1..parties) {
        val thread = thread {
            println("Поток $i начал работу.")
            Thread.sleep(Random.nextLong(1000, 3000))
            println("Поток $i завершил первую фазу и ждет барьера.")
            try {
                barrier.await()
            } catch (e: InterruptedException) {
                println("Thread interrupted: ${Thread.currentThread().name}")
            } catch (e: BrokenBarrierException) {
                println("Barrier broken: ${Thread.currentThread().name}")
            }
            println("Поток $i продолжает работу после барьера.")
        }
        threads.add(thread)
    }

    threads.forEach { it.join() }

    println("Все потоки завершили работу.")
}

// Задача 6: Ограниченный доступ к ресурсу
fun semaphoreExample() {
    val semaphore = Semaphore(2)
    val threads = mutableListOf<Thread>()

    for (i in 1..5) {
        val thread = thread {
            try {
                println("Поток $i пытается получить доступ к ресурсу.")
                semaphore.acquire()
                println("Поток $i получил доступ к ресурсу.")
                Thread.sleep(Random.nextLong(1000, 2000))
            } catch (e: InterruptedException) {
                println("Thread interrupted: ${Thread.currentThread().name}")
            } finally {
                println("Поток $i освобождает ресурс.")
                semaphore.release()
            }
        }
        threads.add(thread)
    }

    threads.forEach { it.join() }

    println("Все потоки завершили работу с ресурсом.")
}

// Задача 7: Обработка результатов задач
fun callableFutureExample() {
    val executor = Executors.newFixedThreadPool(5)
    val futures = mutableListOf<Future<Long>>()

    for (i in 1..10) {
        val task = Callable<Long> {
            val number = Random.nextLong(5, 15)
            println("Вычисление факториала $number в потоке ${Thread.currentThread().name}")
            factorial(number)
        }
        val future = executor.submit(task)
        futures.add(future)
    }

    executor.shutdown()

    for ((index, future) in futures.withIndex()) {
        try {
            val result = future.get()
            println("Результат задачи ${index + 1}: $result")
        } catch (e: InterruptedException) {
            println("Task interrupted: ${Thread.currentThread().name}")
        } catch (e: ExecutionException) {
            println("Exception during task execution: ${e.message}")
        }
    }

    executor.awaitTermination(1, TimeUnit.MINUTES)
}

fun factorial(n: Long): Long {
    var result: Long = 1
    for (i in 1..n) {
        result *= i
    }
    return result
}

// Задача 8: Симуляция производственной линии
fun blockingQueueExample() {
    val queue: BlockingQueue<String> = LinkedBlockingQueue(10)

    val producerThread = thread {
        for (i in 1..20) {
            val data = "Data $i"
            try {
                queue.put(data)
                println("Производитель: добавлено '$data' в очередь.")
                Thread.sleep(Random.nextLong(100, 300))
            } catch (e: InterruptedException) {
                println("Producer thread interrupted.")
            }
        }
        println("Производитель: завершил производство.")
    }

    // Consumer thread
    val consumerThread = thread {
        while (true) {
            try {
                val data = queue.take()
                println("Потребитель: обработано '$data'.")
                Thread.sleep(Random.nextLong(200, 500))
            } catch (e: InterruptedException) {
                println("Consumer thread interrupted.")
                break
            }
        }
    }

    producerThread.join()
    Thread.sleep(1000)
    consumerThread.interrupt()
    consumerThread.join()

    println("Симуляция завершена.")
}

// Задача 9: Многопоточная сортировка
fun parallelSortExample() {
    val array = IntArray(20) { Random.nextInt(1, 100) } // Example array

    println("Исходный массив: ${array.contentToString()}")

    val numThreads = 4
    val chunkSize = array.size / numThreads
    val executor = Executors.newFixedThreadPool(numThreads)
    val futures = mutableListOf<Future<Unit>>()

    for (i in 0 until numThreads) {
        val start = i * chunkSize
        val end = if (i == numThreads - 1) array.size else (i + 1) * chunkSize

        val task = Callable<Unit> {
            val subArray = array.copyOfRange(start, end)
            Arrays.sort(subArray)
            System.arraycopy(subArray, 0, array, start, subArray.size)

            println("Часть массива от $start до $end отсортирована потоком ${Thread.currentThread().name}")
            Unit // Explicitly return Unit
        }

        val future = executor.submit(task)
        futures.add(future)
    }

    executor.shutdown()
    futures.forEach { it.get() }

    Arrays.sort(array)

    println("Отсортированный массив: ${array.contentToString()}")
}

// Задача 10: Обед философов (*)
fun diningPhilosophersExample() {
    val numPhilosophers = 5
    val forks = Array(numPhilosophers) { ReentrantLock() }
    val philosophers = Array(numPhilosophers) {
        Philosopher(it + 1, forks[it], forks[(it + 1) % numPhilosophers])
    }

    val threads = philosophers.map { thread { it.dine() } }

    threads.forEach { it.join() }
}

class Philosopher(val id: Int, val leftFork: ReentrantLock, val rightFork: ReentrantLock) {
    fun dine() {
        try {
            while (true) {
                think()
                if (leftFork.tryLock(10, TimeUnit.MILLISECONDS)) {
                    try {
                        if (rightFork.tryLock(10, TimeUnit.MILLISECONDS)) {
                            try {
                                eat()
                            } finally {
                                rightFork.unlock()
                            }
                        } else {
                            println("Философ $id не смог взять правую вилку.")
                        }
                    } finally {
                        leftFork.unlock()
                    }
                } else {
                    println("Философ $id не смог взять левую вилку.")
                }
            }
        } catch (e: InterruptedException) {
            println("Философ $id завершил обед.")
        }
    }

    fun think() {
        println("Философ $id думает.")
        Thread.sleep(Random.nextLong(100, 500))
    }

    fun eat() {
        println("Философ $id ест.")
        Thread.sleep(Random.nextLong(200, 600))
    }
}

// Задача 11: Расчёт матрицы в параллельных потоках
fun matrixMultiplicationExample() {
    val matrixA = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6)
    )

    val matrixB = arrayOf(
        intArrayOf(7, 8),
        intArrayOf(9, 10),
        intArrayOf(11, 12)
    )

    val result = multiplyMatrices(matrixA, matrixB)

    println("Матрица A:")
    printMatrix(matrixA)
    println("Матрица B:")
    printMatrix(matrixB)
    println("Результат умножения:")
    printMatrix(result)
}

fun multiplyMatrices(matrixA: Array<IntArray>, matrixB: Array<IntArray>): Array<IntArray> {
    val rowsA = matrixA.size
    val colsA = matrixA[0].size
    val colsB = matrixB[0].size

    val result = Array(rowsA) { IntArray(colsB) }
    val executor = Executors.newFixedThreadPool(4)
    val futures = mutableListOf<Future<Unit>>()

    for (i in 0 until rowsA) {
        val task = Callable<Unit> { // Change to Callable<Unit>
            for (j in 0 until colsB) {
                for (k in 0 until colsA) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j]
                }
            }
            println("Строка $i вычислена потоком ${Thread.currentThread().name}")
        }
        val future = executor.submit(task)
        futures.add(future)
    }

    executor.shutdown()
    futures.forEach { it.get() }

    return result
}

fun printMatrix(matrix: Array<IntArray>) {
    for (row in matrix) {
        println(row.contentToString())
    }
}

// Задача 12: Таймер с многопоточностью
fun timerWithThreadsExample() {
    val timer = Timer()
    var counter = 0

    val timerTask = object : TimerTask() {
        override fun run() {
            counter++
            println("Таймер: ${Date()}")
        }
    }

    timer.scheduleAtFixedRate(timerTask, 0, 1000)

    thread {
        Thread.sleep(10000) // Wait 10 seconds
        println("Второй поток: останавливаем таймер.")
        timer.cancel() // Stop the timer
    }

    Thread.sleep(12000)
    println("Основной поток: завершаем работу.")
}