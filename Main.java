public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("John Doe", 30, "Male");
        System.out.println("Информация о человеке:");
        person1.displayInfo();

        person1.increaseAge();
        System.out.println("\nПосле увеличения возраста:");
        person1.displayInfo();

        person1.setName("Jonathan Doe");
        System.out.println("\nПосле изменения имени:");
        person1.displayInfo();
    }
}
class Person {
    private String name;
    private int age;
    private String gender;
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Пол: " + gender);
    }
    public void increaseAge() {
        age++;
    }
    public void setName(String newName) {
        this.name = newName;
    }
}

//// Задача 2
//import java.util.ArrayList;
//import java.util.List;
//
//class Person {
//    private String name;
//    private int age;
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void displayInfo() {
//        System.out.println("Name: " + name);
//        System.out.println("Age: " + age);
//    }
//}
//
//class Worker extends Person {
//    private double salary;
//
//    public Worker(String name, int age, double salary) {
//        super(name, age);
//        this.salary = salary;
//    }
//
//    public double getSalary() {
//        return salary;
//    }
//
//    @Override
//    public void displayInfo() {
//        super.displayInfo();
//        System.out.println("Salary: " + salary);
//    }
//}
//
//class Manager extends Worker {
//    private List<Worker> subordinates;
//
//    public Manager(String name, int age, double salary, List<Worker> subordinates) {
//        super(name, age, salary);
//        this.subordinates = subordinates;
//    }
//
//    public List<Worker> getSubordinates() {
//        return subordinates;
//    }
//
//    public void addSubordinate(Worker worker){
//        subordinates.add(worker);
//    }
//
//    @Override
//    public void displayInfo() {
//        super.displayInfo();
//        System.out.println("Subordinates: ");
//        if (subordinates != null){
//            for (Worker subordinate : subordinates) {
//                System.out.println("  - " + subordinate.getName());
//            }
//        } else {
//            System.out.println("  None.");
//        }
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        // Создание работников
//        Worker worker1 = new Worker("Alice", 30, 50000);
//        Worker worker2 = new Worker("Bob", 25, 45000);
//        Worker worker3 = new Worker("Charlie", 28, 48000);
//        Worker worker4 = new Worker("David", 32, 60000);
//
//
//        // Создание списка подчиненных
//        List<Worker> subordinates = new ArrayList<>();
//        subordinates.add(worker1);
//        subordinates.add(worker2);
//
//        List<Worker> subordinates2 = new ArrayList<>();
//        subordinates2.add(worker3);
//        subordinates2.add(worker4);
//
//        // Создание менеджера
//        Manager manager1 = new Manager("Eve", 35, 70000, subordinates);
//        Manager manager2 = new Manager("Frank", 40, 80000, subordinates2);
//
//        // Вывод информации о работниках и менеджере
//        System.out.println("--- Worker 1 Info ---");
//        worker1.displayInfo();
//        System.out.println("\n--- Worker 2 Info ---");
//        worker2.displayInfo();
//        System.out.println("\n--- Worker 3 Info ---");
//        worker3.displayInfo();
//        System.out.println("\n--- Worker 4 Info ---");
//        worker4.displayInfo();
//
//
//        System.out.println("\n--- Manager 1 Info ---");
//        manager1.displayInfo();
//        System.out.println("\n--- Manager 2 Info ---");
//        manager2.displayInfo();
//    }
//}


//// Задача 3
//import java.util.ArrayList;
//import java.util.List;
//
//// Интерфейс Animal
//interface Animal {
//    void makeSound();
//}
//
//// Класс Dog, реализующий интерфейс Animal
//class Dog implements Animal {
//    @Override
//    public void makeSound() {
//        System.out.println("Woof!");
//    }
//}
//
//// Класс Cat, реализующий интерфейс Animal
//class Cat implements Animal {
//    @Override
//    public void makeSound() {
//        System.out.println("Meow!");
//    }
//}
//
//// Класс Cow, реализующий интерфейс Animal
//class Cow implements Animal {
//    @Override
//    public void makeSound() {
//        System.out.println("Moo!");
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        // Создание массива животных
//        List<Animal> animals = new ArrayList<>();
//        animals.add(new Dog());
//        animals.add(new Cat());
//        animals.add(new Cow());
//
//        // Вызов метода makeSound для каждого животного в массиве
//        for (Animal animal : animals) {
//            animal.makeSound(); // Полиморфный вызов
//        }
//    }
//}


//// Задача 4
//public class Main {
//
//    // Абстрактный класс Transport
//    abstract static class Transport {
//        public abstract void move(); // Абстрактный метод move()
//    }
//
//    // Класс Car, наследующийся от Transport
//    static class Car extends Transport {
//        @Override
//        public void move() {
//            System.out.println("Car is moving on the road.");
//        }
//    }
//
//    // Класс Bike, наследующийся от Transport
//    static class Bike extends Transport {
//        @Override
//        public void move() {
//            System.out.println("Bike is moving on the bike path.");
//        }
//    }
//
//    public static void main(String[] args) {
//        // Создание объектов Car и Bike
//        Transport car = new Car();
//        Transport bike = new Bike();
//
//        // Вызов метода move() для каждого объекта
//        System.out.println("--- Car Move ---");
//        car.move(); // Выведет "Car is moving on the road."
//        System.out.println("--- Bike Move ---");
//        bike.move(); // Выведет "Bike is moving on the bike path."
//    }
//}


//// Задача 5
//import java.util.ArrayList;
//import java.util.List;
//
//class Book {
//    private String title;
//    private String author;
//    private int year;
//
//    public Book(String title, String author, int year) {
//        this.title = title;
//        this.author = author;
//        this.year = year;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    @Override
//    public String toString() {
//        return "Title: " + title + ", Author: " + author + ", Year: " + year;
//    }
//}
//
//class Library {
//    private List<Book> books;
//
//    public Library() {
//        this.books = new ArrayList<>();
//    }
//
//    public void addBook(Book book) {
//        this.books.add(book);
//    }
//
//    public List<Book> findBooksByAuthor(String author) {
//        List<Book> result = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getAuthor().equalsIgnoreCase(author)) {
//                result.add(book);
//            }
//        }
//        return result;
//    }
//
//    public List<Book> findBooksByYear(int year) {
//        List<Book> result = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getYear() == year) {
//                result.add(book);
//            }
//        }
//        return result;
//    }
//
//    public void displayAllBooks(){
//        if (books.isEmpty()){
//            System.out.println("Библиотека пуста.");
//        } else {
//            System.out.println("Все книги в библиотеке:");
//            for (Book book: books){
//                System.out.println(book.toString());
//            }
//        }
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        // Создание библиотеки
//        Library library = new Library();
//
//        // Добавление книг
//        library.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954));
//        library.addBook(new Book("Pride and Prejudice", "Jane Austen", 1813));
//        library.addBook(new Book("1984", "George Orwell", 1949));
//        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", 1937));
//        library.addBook(new Book("Jane Eyre", "Charlotte Brontë", 1847));
//
//        library.displayAllBooks();
//        System.out.println("\nПоиск книг по автору 'J.R.R. Tolkien':");
//        List<Book> tolkienBooks = library.findBooksByAuthor("J.R.R. Tolkien");
//        for (Book book : tolkienBooks) {
//            System.out.println(book.toString());
//        }
//        System.out.println("\nПоиск книг по году публикации 1813:");
//        List<Book> yearBooks = library.findBooksByYear(1813);
//        for (Book book : yearBooks) {
//            System.out.println(book.toString());
//        }
//
//    }
//}


//// Задача 6
//import java.util.Random;
//
//public class Main {
//    public static void main(String[] args) {
//        // Создаем несколько банковских счетов
//        BankAccount account1 = new BankAccount(generateAccountNumber(), 1000.0);
//        BankAccount account2 = new BankAccount(generateAccountNumber(), 500.0);
//
//
//        // Пополняем баланс
//        account1.deposit(500.0);
//        System.out.println("Баланс счета " + account1.getAccountNumber() + ": " + account1.getBalance());
//
//        // Снимаем средства
//        try{
//            account1.withdraw(200.0);
//            System.out.println("Баланс счета " + account1.getAccountNumber() + ": " + account1.getBalance());
//        } catch (IllegalArgumentException e){
//            System.out.println("Ошибка снятия средств на счете " + account1.getAccountNumber() + ": " + e.getMessage());
//        }
//        try{
//            account2.withdraw(600.0);
//            System.out.println("Баланс счета " + account2.getAccountNumber() + ": " + account2.getBalance());
//        } catch (IllegalArgumentException e){
//            System.out.println("Ошибка снятия средств на счете " + account2.getAccountNumber() + ": " + e.getMessage());
//        }
//
//
//        // Выводим текущие балансы
//        System.out.println("\n--- Текущие балансы ---");
//        System.out.println("Баланс счета " + account1.getAccountNumber() + ": " + account1.getBalance());
//        System.out.println("Баланс счета " + account2.getAccountNumber() + ": " + account2.getBalance());
//
//    }
//
//    // Функция для генерации номера банковского счета
//    private static String generateAccountNumber() {
//        Random random = new Random();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 10; i++){
//            sb.append(random.nextInt(10));
//        }
//        return sb.toString();
//    }
//}
//
//class BankAccount {
//    private String accountNumber;
//    private double balance;
//
//    public BankAccount(String accountNumber, double initialBalance) {
//        this.accountNumber = accountNumber;
//        if (initialBalance < 0) {
//            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным.");
//        }
//        this.balance = initialBalance;
//    }
//
//    public String getAccountNumber() {
//        return accountNumber;
//    }
//
//    public double getBalance() {
//        return balance;
//    }
//
//    public void deposit(double amount) {
//        if (amount <= 0) {
//            throw new IllegalArgumentException("Сумма пополнения должна быть положительной.");
//        }
//        balance += amount;
//    }
//
//    public void withdraw(double amount) {
//        if (amount <= 0) {
//            throw new IllegalArgumentException("Сумма снятия должна быть положительной.");
//        }
//        if (balance < amount) {
//            throw new IllegalArgumentException("Недостаточно средств на счете.");
//        }
//        balance -= amount;
//    }
//}


//// Задача 7
//public class Main {
//    public static void main(String[] args) {
//        Counter counter1 = new Counter();
//        Counter counter2 = new Counter();
//        Counter counter3 = new Counter();
//
//        System.out.println("Количество созданных объектов: " + Counter.getObjectCount());
//
//        Counter counter4 = new Counter();
//        System.out.println("Количество созданных объектов: " + Counter.getObjectCount());
//
//        Counter counter5 = new Counter();
//        Counter counter6 = new Counter();
//        System.out.println("Количество созданных объектов: " + Counter.getObjectCount());
//
//    }
//}
//
//class Counter {
//    private static int objectCount = 0;
//
//    public Counter() {
//        objectCount++;
//    }
//
//    public static int getObjectCount() {
//        return objectCount;
//    }
//}


//// Задача 8
//import java.lang.Math;
//
//// Абстрактный класс Shape
//abstract class Shape {
//    public abstract double getArea();
//}
//
//// Класс Circle (круг), наследуется от Shape
//class Circle extends Shape {
//    private double radius;
//
//    public Circle(double radius) {
//        this.radius = radius;
//    }
//
//    @Override
//    public double getArea() {
//        return Math.PI * radius * radius;
//    }
//}
//
//// Класс Rectangle (прямоугольник), наследуется от Shape
//class Rectangle extends Shape {
//    private double length;
//    private double width;
//
//    public Rectangle(double length, double width) {
//        this.length = length;
//        this.width = width;
//    }
//
//    @Override
//    public double getArea() {
//        return length * width;
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        // Создание объектов
//        Circle circle = new Circle(5);
//        Rectangle rectangle = new Rectangle(4, 6);
//
//        // Вывод площади
//        System.out.println("Площадь круга: " + circle.getArea());
//        System.out.println("Площадь прямоугольника: " + rectangle.getArea());
//    }
//}


//// Задача 9
//public class Main {
//
//    // Базовый класс Animal
//    static class Animal {
//        public void move() {
//            System.out.println("Animal moves.");
//        }
//    }
//
//    // Класс Fish, наследуется от Animal
//    static class Fish extends Animal {
//        @Override
//        public void move() {
//            System.out.println("Fish swims.");
//        }
//    }
//
//    // Класс Bird, наследуется от Animal
//    static class Bird extends Animal {
//        @Override
//        public void move() {
//            System.out.println("Bird flies.");
//        }
//    }
//
//    // Класс Dog, наследуется от Animal
//    static class Dog extends Animal {
//        @Override
//        public void move() {
//            System.out.println("Dog runs.");
//        }
//    }
//
//    public static void main(String[] args) {
//        // Создание объектов разных классов
//        Animal animal = new Animal();
//        Fish fish = new Fish();
//        Bird bird = new Bird();
//        Dog dog = new Dog();
//
//        // Вызов метода move() для каждого объекта
//        System.out.println("--- Animal move ---");
//        animal.move();
//        System.out.println("\n--- Fish move ---");
//        fish.move();
//        System.out.println("\n--- Bird move ---");
//        bird.move();
//        System.out.println("\n--- Dog move ---");
//        dog.move();
//    }
//}


//// Задача 10
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Main {
//
//    // Класс Student
//    static class Student {
//        String name;
//        String group;
//        double grade;
//
//        public Student(String name, String group, double grade) {
//            this.name = name;
//            this.group = group;
//            this.grade = grade;
//        }
//
//        public String getName(){
//            return name;
//        }
//
//        public double getGrade(){
//            return grade;
//        }
//
//        @Override
//        public String toString() {
//            return "Student{" +
//                    "name='" + name + '\'' +
//                    ", group='" + group + '\'' +
//                    ", grade=" + grade +
//                    '}';
//        }
//    }
//
//
//    // Класс University
//    static class University {
//        List<Student> students = new ArrayList<>();
//
//
//        public void addStudent(Student student) {
//            students.add(student);
//        }
//
//        public void sortStudentsByName() {
//            students.sort(Comparator.comparing(student -> student.name));
//        }
//
//
//        public List<Student> filterByGrade(double minGrade) {
//            return students.stream()
//                    .filter(student -> student.grade >= minGrade)
//                    .collect(Collectors.toList());
//        }
//
//        public void displayStudents() {
//            if (students == null || students.isEmpty()) {
//                System.out.println("Список студентов пуст.");
//                return;
//            }
//            students.forEach(System.out::println);
//        }
//    }
//
//
//    public static void main(String[] args) {
//        // Создаем университет
//        University university = new University();
//
//        // Создаем студентов
//        Student student1 = new Student("Alice", "Group A", 85.5);
//        Student student2 = new Student("Bob", "Group B", 92.0);
//        Student student3 = new Student("Charlie", "Group A", 78.0);
//        Student student4 = new Student("David", "Group C", 90.5);
//        Student student5 = new Student("Eva", "Group B", 88.0);
//
//        // Добавляем студентов в университет
//        university.addStudent(student1);
//        university.addStudent(student2);
//        university.addStudent(student3);
//        university.addStudent(student4);
//        university.addStudent(student5);
//
//
//        // Выводим всех студентов до сортировки
//        System.out.println("--- Students before sorting ---");
//        university.displayStudents();
//
//        // Сортируем студентов по имени
//        university.sortStudentsByName();
//
//        // Выводим всех студентов после сортировки по имени
//        System.out.println("\n--- Students after sorting by name ---");
//        university.displayStudents();
//
//        // Фильтруем студентов по успеваемости
//        List<Student> excellentStudents = university.filterByGrade(90.0);
//        System.out.println("\n--- Students with grade >= 90 ---");
//        excellentStudents.forEach(System.out::println);
//
//        // Фильтруем студентов по успеваемости
//        List<Student> goodStudents = university.filterByGrade(80.0);
//        System.out.println("\n--- Students with grade >= 80 ---");
//        goodStudents.forEach(System.out::println);
//
//
//        // Пытаемся получить студентов с минимальной оценкой 100.0
//        List<Student> emptyStudents = university.filterByGrade(100.0);
//        System.out.println("\n--- Students with grade >= 100 ---");
//        emptyStudents.forEach(System.out::println);
//
//
//        // Тест на пустой список студентов
//        System.out.println("\n--- Empty student list test ---");
//        University university2 = new University();
//        university2.displayStudents();
//    }
//}


//// Задача 11
//import java.util.ArrayList;
//import java.util.List;
//
//public class Main {
//
//    static class Product {
//        private String name;
//        private double price;
//        private int quantity;
//
//        public Product(String name, double price, int quantity) {
//            this.name = name;
//            this.price = price;
//            this.quantity = quantity;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public double getPrice() {
//            return price;
//        }
//
//        public int getQuantity() {
//            return quantity;
//        }
//
//        public void setQuantity(int quantity) {
//            this.quantity = quantity;
//        }
//
//        @Override
//        public String toString() {
//            return "Product{" +
//                    "name='" + name + '\'' +
//                    ", price=" + price +
//                    ", quantity=" + quantity +
//                    '}';
//        }
//    }
//
//    static class Store {
//        private List<Product> products;
//
//        public Store() {
//            this.products = new ArrayList<>();
//        }
//
//        public void addProduct(Product product) {
//            products.add(product);
//        }
//
//        public void removeProduct(String name) {
//            products.removeIf(product -> product.getName().equals(name));
//        }
//
//        public Product findProduct(String name) {
//            for (Product product : products) {
//                if (product.getName().equals(name)) {
//                    return product;
//                }
//            }
//            return null; // Возвращаем null если продукт не найден
//        }
//
//        public void displayAllProducts() {
//            if (products.isEmpty()) {
//                System.out.println("В магазине нет товаров.");
//                return;
//            }
//            System.out.println("Список товаров в магазине:");
//            for(Product product : products){
//                System.out.println(product);
//            }
//        }
//
//        public void changeProductQuantity(String name, int quantity) {
//            Product product = findProduct(name);
//            if (product != null) {
//                product.setQuantity(quantity);
//                System.out.println("Количество товара '" + name + "' изменено на: " + quantity + ".");
//            } else {
//                System.out.println("Товар '" + name + "' не найден в магазине.");
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        // Создаем магазин
//        Store store = new Store();
//
//        // Создаем продукты
//        Product product1 = new Product("Laptop", 1200.0, 10);
//        Product product2 = new Product("Mouse", 25.0, 50);
//        Product product3 = new Product("Keyboard", 75.0, 30);
//        Product product4 = new Product("Monitor", 300.0, 10);
//
//        // Добавляем продукты в магазин
//        store.addProduct(product1);
//        store.addProduct(product2);
//        store.addProduct(product3);
//        store.addProduct(product4);
//
//        // Отображаем все продукты в магазине
//        store.displayAllProducts();
//
//
//        // Находим товар по имени
//        String searchName = "Mouse";
//        Product foundProduct = store.findProduct(searchName);
//        if (foundProduct != null) {
//            System.out.println("\nНайден товар: " + foundProduct);
//        } else {
//            System.out.println("\nТовар '" + searchName + "' не найден.");
//        }
//
//
//        //Изменяем количество товара
//        store.changeProductQuantity("Keyboard", 15);
//        store.changeProductQuantity("Headphones", 20);
//
//        // Отображаем все продукты в магазине
//        store.displayAllProducts();
//
//        // Удаляем товар
//        store.removeProduct("Mouse");
//        System.out.println("\nПосле удаления товара 'Mouse'.");
//        // Отображаем все продукты в магазине
//        store.displayAllProducts();
//    }
//}


//// Задача 12
//import java.util.Random;
//// Интерфейс платежной системы
//interface PaymentSystem {
//    boolean pay(double amount);
//    boolean refund(double amount);
//}
//
//// Класс CreditCard
//class CreditCard implements PaymentSystem {
//    private String cardNumber;
//    private String expiryDate;
//    private String cvv;
//
//    public CreditCard(String cardNumber, String expiryDate, String cvv) {
//        this.cardNumber = cardNumber;
//        this.expiryDate = expiryDate;
//        this.cvv = cvv;
//    }
//
//    @Override
//    public boolean pay(double amount) {
//        // Имитация процесса оплаты
//        System.out.println("Производится оплата картой: " + cardNumber + ", на сумму: " + amount);
//        Random random = new Random();
//        if (random.nextDouble() > 0.2){
//            System.out.println("Оплата картой успешна.");
//            return true;
//        }
//        System.out.println("Ошибка оплаты картой.");
//        return false;
//
//    }
//
//    @Override
//    public boolean refund(double amount) {
//        // Имитация процесса возврата средств
//        System.out.println("Производится возврат средств на карту: " + cardNumber + ", на сумму: " + amount);
//        Random random = new Random();
//        if (random.nextDouble() > 0.2){
//            System.out.println("Возврат средств на карту успешен.");
//            return true;
//        }
//        System.out.println("Ошибка возврата средств на карту.");
//        return false;
//    }
//}
//
//// Класс PayPal
//class PayPal implements PaymentSystem {
//    private String email;
//    private String password;
//
//    public PayPal(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
//
//    @Override
//    public boolean pay(double amount) {
//        // Имитация процесса оплаты
//        System.out.println("Производится оплата через PayPal: " + email + ", на сумму: " + amount);
//        Random random = new Random();
//        if (random.nextDouble() > 0.2){
//            System.out.println("Оплата через PayPal успешна.");
//            return true;
//        }
//        System.out.println("Ошибка оплаты через PayPal.");
//        return false;
//    }
//
//    @Override
//    public boolean refund(double amount) {
//        // Имитация процесса возврата средств
//        System.out.println("Производится возврат средств через PayPal: " + email + ", на сумму: " + amount);
//        Random random = new Random();
//        if(random.nextDouble() > 0.2){
//            System.out.println("Возврат средств через PayPal успешен.");
//            return true;
//        }
//        System.out.println("Ошибка возврата средств через PayPal.");
//        return false;
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        // Создание платежных систем
//        PaymentSystem creditCard = new CreditCard("1234-5678-9012-3456", "12/25", "123");
//        PaymentSystem payPal = new PayPal("test@example.com", "password123");
//
//        // Пример использования
//        System.out.println("--- Оплата картой ---");
//        creditCard.pay(100.0);
//
//        System.out.println("\n--- Возврат средств на карту ---");
//        creditCard.refund(50.0);
//
//        System.out.println("\n--- Оплата через PayPal ---");
//        payPal.pay(75.0);
//
//        System.out.println("\n--- Возврат средств через PayPal ---");
//        payPal.refund(25.0);
//    }
//}


//// Задача 13
//public class Main {
//    public static void main(String[] args) {
//        // Создаем несколько объектов разных классов и выводим их id
//        MyClass obj1 = new MyClass();
//        MyClass obj2 = new MyClass();
//        AnotherClass obj3 = new AnotherClass();
//        MyClass obj4 = new MyClass();
//        AnotherClass obj5 = new AnotherClass();
//
//
//        System.out.println("Object 1 ID: " + obj1.getId());
//        System.out.println("Object 2 ID: " + obj2.getId());
//        System.out.println("Object 3 ID: " + obj3.getId());
//        System.out.println("Object 4 ID: " + obj4.getId());
//        System.out.println("Object 5 ID: " + obj5.getId());
//
//
//        // Проверка, что каждый класс имеет свой счетчик
//        System.out.println("MyClass counter: " + UniqueID.getMyClassCounter());
//        System.out.println("AnotherClass counter: " + UniqueID.getAnotherClassCounter());
//    }
//}
//class UniqueID {
//    private static int myClassCounter = 0;
//    private static int anotherClassCounter = 0;
//
//    public static int generateMyClassID() {
//        return ++myClassCounter;
//    }
//    public static int generateAnotherClassID() {
//        return ++anotherClassCounter;
//    }
//    public static int getMyClassCounter() {
//        return myClassCounter;
//    }
//    public static int getAnotherClassCounter() {
//        return anotherClassCounter;
//    }
//}
//
//
//class MyClass {
//    private final int id;
//
//    public MyClass() {
//        this.id = UniqueID.generateMyClassID();
//    }
//
//    public int getId() {
//        return id;
//    }
//}
//
//class AnotherClass {
//    private final int id;
//
//    public AnotherClass() {
//        this.id = UniqueID.generateAnotherClassID();
//    }
//
//    public int getId() {
//        return id;
//    }
//}


//// Задача 14
//public class Main {
//    public static void main(String[] args) {
//        // Создаем точки
//        Point topLeft = new Point(1, 5);
//        Point bottomRight = new Point(7, 1);
//
//        // Создаем прямоугольник
//        Rectangle rectangle = new Rectangle(topLeft, bottomRight);
//
//        // Вычисляем и выводим площадь
//        double area = rectangle.calculateArea();
//        System.out.println("Площадь прямоугольника: " + area);
//
//        // Создаем прямоугольник с отрицательными координатами
//        Point topLeft2 = new Point(-1, -5);
//        Point bottomRight2 = new Point(-7, -1);
//        Rectangle rectangle2 = new Rectangle(topLeft2,bottomRight2);
//
//        double area2 = rectangle2.calculateArea();
//        System.out.println("Площадь прямоугольника 2: " + area2);
//    }
//}
//
//class Point {
//    private int x;
//    private int y;
//
//    public Point(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//}
//
//class Rectangle {
//    private Point topLeft;
//    private Point bottomRight;
//
//    public Rectangle(Point topLeft, Point bottomRight) {
//        this.topLeft = topLeft;
//        this.bottomRight = bottomRight;
//    }
//
//    public double calculateArea() {
//        int width = Math.abs(bottomRight.getX() - topLeft.getX());
//        int height = Math.abs(bottomRight.getY() - topLeft.getY());
//        return width * height;
//    }
//}


//// Задача 15
//public class Main {
//    public static void main(String[] args) {
//        // Создание комплексных чисел
//        ComplexNumber c1 = new ComplexNumber(3, 2);
//        ComplexNumber c2 = new ComplexNumber(1, -1);
//
//        // Выполнение операций
//        ComplexNumber sum = c1.add(c2);
//        ComplexNumber diff = c1.subtract(c2);
//        ComplexNumber prod = c1.multiply(c2);
//        ComplexNumber quot = c1.divide(c2);
//
//
//        // Вывод результатов
//        System.out.println("Complex number 1: " + c1);
//        System.out.println("Complex number 2: " + c2);
//        System.out.println("Sum: " + sum);
//        System.out.println("Difference: " + diff);
//        System.out.println("Product: " + prod);
//        System.out.println("Quotient: " + quot);
//    }
//}
//class ComplexNumber {
//    private final double real;
//    private final double imaginary;
//
//    public ComplexNumber(double real, double imaginary) {
//        this.real = real;
//        this.imaginary = imaginary;
//    }
//
//    public ComplexNumber add(ComplexNumber other) {
//        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
//    }
//
//    public ComplexNumber subtract(ComplexNumber other) {
//        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
//    }
//
//    public ComplexNumber multiply(ComplexNumber other) {
//        double realPart = (this.real * other.real) - (this.imaginary * other.imaginary);
//        double imaginaryPart = (this.real * other.imaginary) + (this.imaginary * other.real);
//        return new ComplexNumber(realPart, imaginaryPart);
//    }
//
//    public ComplexNumber divide(ComplexNumber other) {
//        double denominator = other.real * other.real + other.imaginary * other.imaginary;
//        if (denominator == 0) {
//            throw new ArithmeticException("Деление на ноль.");
//        }
//        double realPart = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
//        double imaginaryPart = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
//        return new ComplexNumber(realPart, imaginaryPart);
//    }
//
//    @Override
//    public String toString() {
//        if (imaginary >= 0) {
//            return real + " + " + imaginary + "i";
//        } else {
//            return real + " - " + -imaginary + "i";
//        }
//    }
//}


//// Задача 16
//public class Main {
//    public static void main(String[] args) {
//        // Создание матриц
//        int[][] data1 = {{1, 2}, {3, 4}};
//        Matrix matrix1 = new Matrix(data1);
//
//        int[][] data2 = {{5, 6}, {7, 8}};
//        Matrix matrix2 = new Matrix(data2);
//
//        int[][] data3 = {{1, 2, 3}, {4, 5, 6}};
//        Matrix matrix3 = new Matrix(data3);
//
//        int[][] data4 = {{7, 8}, {9, 10}, {11,12}};
//        Matrix matrix4 = new Matrix(data4);
//
//
//        // Вывод исходных матриц
//        System.out.println("Matrix 1:");
//        matrix1.printMatrix();
//        System.out.println("\nMatrix 2:");
//        matrix2.printMatrix();
//        System.out.println("\nMatrix 3:");
//        matrix3.printMatrix();
//        System.out.println("\nMatrix 4:");
//        matrix4.printMatrix();
//
//        // Сложение матриц
//        System.out.println("\nMatrix 1 + Matrix 2:");
//        try {
//            Matrix sumMatrix = matrix1.add(matrix2);
//            sumMatrix.printMatrix();
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        // Попытка сложения матриц разного размера
//        System.out.println("\nMatrix 1 + Matrix 3:");
//        try {
//            Matrix sumMatrix = matrix1.add(matrix3);
//            sumMatrix.printMatrix();
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//
//        // Умножение матриц
//        System.out.println("\nMatrix 1 * Matrix 2:");
//        try {
//            Matrix productMatrix = matrix1.multiply(matrix2);
//            productMatrix.printMatrix();
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        // Попытка умножения матриц неподходящего размера
//        System.out.println("\nMatrix 1 * Matrix 3:");
//        try {
//            Matrix productMatrix = matrix1.multiply(matrix3);
//            productMatrix.printMatrix();
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        // Умножение матриц
//        System.out.println("\nMatrix 3 * Matrix 4:");
//        try {
//            Matrix productMatrix = matrix3.multiply(matrix4);
//            productMatrix.printMatrix();
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//
//    }
//}
//class Matrix {
//    private int[][] data;
//    private int rows;
//    private int cols;
//
//    public Matrix(int[][] data) {
//        this.data = data;
//        this.rows = data.length;
//        this.cols = (rows > 0) ? data[0].length : 0;
//    }
//
//    public Matrix add(Matrix other) {
//        if (this.rows != other.rows || this.cols != other.cols) {
//            throw new IllegalArgumentException("Matrices must have the same dimensions for addition.");
//        }
//        int[][] result = new int[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                result[i][j] = this.data[i][j] + other.data[i][j];
//            }
//        }
//        return new Matrix(result);
//    }
//
//    public Matrix multiply(Matrix other) {
//        if (this.cols != other.rows) {
//            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix for multiplication.");
//        }
//        int[][] result = new int[this.rows][other.cols];
//        for (int i = 0; i < this.rows; i++) {
//            for (int j = 0; j < other.cols; j++) {
//                for (int k = 0; k < this.cols; k++){
//                    result[i][j] += this.data[i][k] * other.data[k][j];
//                }
//            }
//        }
//        return new Matrix(result);
//    }
//
//    public void printMatrix() {
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                System.out.print(data[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
//}


//// Задача 17
//import java.util.Random;
//
//public class Main {
//    public static void main(String[] args) {
//        // Создание персонажа игрока и оружия
//        Weapon sword = new Weapon("Sword", 15);
//        Player player = new Player("Hero", 100, sword);
//
//        // Создание врагов
//        Enemy goblin = new Enemy("Goblin", 50, 10);
//        Enemy dragon = new Enemy("Dragon", 150, 25);
//
//        // Начало игры
//        System.out.println("A new adventure begins!");
//        System.out.println("You, " + player.getName() + ", are armed with a " + player.getWeapon().getName() + ".");
//        System.out.println("--------------------");
//
//        // Атака гоблина
//        player.attack(goblin);
//
//        System.out.println("--------------------");
//
//        // Атака дракона
//        player.attack(dragon);
//
//        System.out.println("--------------------");
//
//        // Концовка игры
//        if (goblin.getHealth() <= 0 && dragon.getHealth() <= 0){
//            System.out.println("You have defeated all the enemies! Congratulations!");
//        } else if (player.getHealth() <= 0) {
//            System.out.println("Game Over! You were defeated by the enemies!");
//        } else {
//            System.out.println("The battle continues!");
//        }
//    }
//}
//
//class Weapon {
//    private String name;
//    private int damage;
//
//    public Weapon(String name, int damage) {
//        this.name = name;
//        this.damage = damage;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getDamage() {
//        return damage;
//    }
//}
//
//
//class Player {
//    private String name;
//    private int health;
//    private Weapon weapon;
//
//    public Player(String name, int health, Weapon weapon) {
//        this.name = name;
//        this.health = health;
//        this.weapon = weapon;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getHealth() {
//        return health;
//    }
//
//    public void setHealth(int health){
//        this.health = health;
//    }
//
//    public Weapon getWeapon() {
//        return weapon;
//    }
//
//    public void attack(Enemy enemy) {
//        Random random = new Random();
//        int hit = random.nextInt(2);
//        if(hit == 1){
//            int damage = weapon.getDamage();
//            enemy.takeDamage(damage);
//            System.out.println(name + " attacks " + enemy.getName() + " with " + weapon.getName() + " and deals " + damage + " damage.");
//        } else {
//            System.out.println(name + " attacks " + enemy.getName() + ", but missed the target!");
//        }
//
//        if (enemy.getHealth() <= 0) {
//            System.out.println(enemy.getName() + " is defeated!");
//        }
//    }
//}
//
//class Enemy {
//    private String name;
//    private int health;
//    private int damage;
//
//    public Enemy(String name, int health, int damage) {
//        this.name = name;
//        this.health = health;
//        this.damage = damage;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getHealth() {
//        return health;
//    }
//
//    public void takeDamage(int damage) {
//        this.health -= damage;
//        if (this.health < 0){
//            this.health = 0;
//        }
//    }
//}


//// Задача 18
//import java.util.ArrayList;
//import java.util.List;
//
//class Product {
//    private String name;
//    private double price;
//
//    public Product(String name, double price) {
//        this.name = name;
//        this.price = price;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "name='" + name + '\'' +
//                ", price=" + price +
//                '}';
//    }
//}
//
//class Order {
//    private int orderId;
//    private Customer customer;
//    private List<Product> products;
//
//    public Order(int orderId, Customer customer) {
//        this.orderId = orderId;
//        this.customer = customer;
//        this.products = new ArrayList<>();
//    }
//
//    public void addProduct(Product product) {
//        this.products.add(product);
//    }
//
//    public double getTotalCost() {
//        double total = 0;
//        for (Product product : products) {
//            total += product.getPrice();
//        }
//        return total;
//    }
//
//    public int getOrderId(){
//        return orderId;
//    }
//
//    public Customer getCustomer(){
//        return customer;
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    @Override
//    public String toString() {
//        return "Order{" +
//                "orderId=" + orderId +
//                ", customer=" + customer +
//                ", products=" + products +
//                '}';
//    }
//}
//
//class Customer {
//    private String name;
//    private String email;
//
//    public Customer(String name, String email) {
//        this.name = name;
//        this.email = email;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    @Override
//    public String toString() {
//        return "Customer{" +
//                "name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
//}
//
//public class Main {
//    private static List<Order> orderHistory = new ArrayList<>();
//    public static void main(String[] args) {
//
//        // Создание продуктов
//        Product product1 = new Product("Laptop", 1200.00);
//        Product product2 = new Product("Keyboard", 75.00);
//        Product product3 = new Product("Mouse", 25.00);
//
//        // Создание клиентов
//        Customer customer1 = new Customer("John Doe", "john.doe@example.com");
//        Customer customer2 = new Customer("Jane Smith", "jane.smith@example.com");
//
//        // Создание заказов
//        Order order1 = new Order(1, customer1);
//        order1.addProduct(product1);
//        order1.addProduct(product2);
//        orderHistory.add(order1);
//
//        Order order2 = new Order(2, customer2);
//        order2.addProduct(product2);
//        order2.addProduct(product3);
//        orderHistory.add(order2);
//
//        // Добавление продуктов в заказ
//
//        System.out.println("===Заказы===");
//        printOrderHistory();
//
//        System.out.println("\n=== Информация о заказе 1 ===");
//        printOrderDetails(order1);
//
//        System.out.println("\n=== Информация о заказе 2 ===");
//        printOrderDetails(order2);
//    }
//
//    private static void printOrderHistory(){
//        for(Order order : orderHistory){
//            System.out.println(order);
//
//        }
//
//    }
//
//    private static void printOrderDetails(Order order){
//        System.out.println("ID заказа: " + order.getOrderId());
//        System.out.println("Клиент: " + order.getCustomer().getName() + " (" + order.getCustomer().getEmail()+ ")");
//        System.out.println("Товары:");
//        for (Product product : order.getProducts()) {
//            System.out.println("  - " + product.getName() + ": $" + product.getPrice());
//        }
//        System.out.println("Общая стоимость: $" + order.getTotalCost());
//    }
//}


//// Задача 19
//class Device {
//    String brand;
//
//    public Device(String brand) {
//        this.brand = brand;
//    }
//
//    public void turnOn() {
//        System.out.println(brand + " is turned ON");
//    }
//
//    public void turnOff() {
//        System.out.println(brand + " is turned OFF");
//    }
//}
//
//class Smartphone extends Device {
//    public Smartphone(String brand) {
//        super(brand);
//    }
//
//    public void takePhoto() {
//        System.out.println(brand + " takes a photo");
//    }
//}
//
//class Laptop extends Device {
//    public Laptop(String brand) {
//        super(brand);
//    }
//
//    public void openBrowser() {
//        System.out.println(brand + " opens the browser");
//    }
//}
//
//
//public class Main {
//    public static void main(String[] args) {
//        Smartphone smartphone = new Smartphone("Samsung");
//        Laptop laptop = new Laptop("Apple");
//
//        smartphone.turnOn();
//        smartphone.takePhoto();
//        smartphone.turnOff();
//
//        System.out.println();
//
//        laptop.turnOn();
//        laptop.openBrowser();
//        laptop.turnOff();
//    }
//}


//// Задача 20
//import java.util.Scanner;
//
//class Player {
//    private String name;
//    private char symbol;
//
//    public Player(String name, char symbol) {
//        this.name = name;
//        this.symbol = symbol;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public char getSymbol() {
//        return symbol;
//    }
//}
//
//class Game {
//    private char[][] board;
//    private Player player1;
//    private Player player2;
//    private Player currentPlayer;
//    private boolean gameFinished;
//
//    public Game(String player1Name, String player2Name) {
//        board = new char[3][3];
//        player1 = new Player(player1Name, 'X');
//        player2 = new Player(player2Name, 'O');
//        currentPlayer = player1;
//        gameFinished = false;
//        initializeBoard();
//    }
//
//    private void initializeBoard() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                board[i][j] = '-';
//            }
//        }
//    }
//
//    public void play() {
//        Scanner scanner = new Scanner(System.in);
//        printBoard();
//
//        while (!gameFinished) {
//            System.out.println(currentPlayer.getName() + ", your turn (" + currentPlayer.getSymbol() + ")");
//            int row, col;
//            while (true) {
//                System.out.print("Enter row (0-2): ");
//                row = scanner.nextInt();
//                System.out.print("Enter column (0-2): ");
//                col = scanner.nextInt();
//
//                if (isValidMove(row, col)) {
//                    break;
//                } else {
//                    System.out.println("Invalid move. Try again.");
//                }
//            }
//            board[row][col] = currentPlayer.getSymbol();
//            printBoard();
//            if (checkWin()) {
//                System.out.println(currentPlayer.getName() + " wins!");
//                gameFinished = true;
//            } else if (isBoardFull()) {
//                System.out.println("It's a draw!");
//                gameFinished = true;
//            } else {
//                switchPlayer();
//            }
//
//        }
//        scanner.close();
//    }
//
//    private boolean isValidMove(int row, int col) {
//        if (row < 0 || row > 2 || col < 0 || col > 2) {
//            return false;
//        }
//        return board[row][col] == '-';
//    }
//
//    private void printBoard() {
//        System.out.println("-------------");
//        for (int i = 0; i < 3; i++) {
//            System.out.print("| ");
//            for (int j = 0; j < 3; j++) {
//                System.out.print(board[i][j] + " | ");
//            }
//            System.out.println();
//            System.out.println("-------------");
//        }
//    }
//
//    private boolean checkWin() {
//        // Проверяем строки
//        for(int i = 0; i < 3; i++) {
//            if(board[i][0] == currentPlayer.getSymbol() && board[i][1] == currentPlayer.getSymbol() && board[i][2] == currentPlayer.getSymbol()){
//                return true;
//            }
//        }
//
//        // Проверяем столбцы
//        for(int i = 0; i < 3; i++) {
//            if(board[0][i] == currentPlayer.getSymbol() && board[1][i] == currentPlayer.getSymbol() && board[2][i] == currentPlayer.getSymbol()){
//                return true;
//            }
//        }
//
//
//        // Проверяем диагонали
//        if(board[0][0] == currentPlayer.getSymbol() && board[1][1] == currentPlayer.getSymbol() && board[2][2] == currentPlayer.getSymbol()){
//            return true;
//        }
//
//        if(board[0][2] == currentPlayer.getSymbol() && board[1][1] == currentPlayer.getSymbol() && board[2][0] == currentPlayer.getSymbol()){
//            return true;
//        }
//        return false;
//    }
//
//    private boolean isBoardFull() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j] == '-') {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private void switchPlayer() {
//        currentPlayer = (currentPlayer == player1) ? player2 : player1;
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter player 1 name:");
//        String player1Name = scanner.nextLine();
//        System.out.println("Enter player 2 name:");
//        String player2Name = scanner.nextLine();
//
//
//        Game game = new Game(player1Name, player2Name);
//        game.play();
//        scanner.close();
//    }
//}