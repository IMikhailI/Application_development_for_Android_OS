package org.example.lab3


fun findAverage(numbers: List<Double>): Double {
    var sum = 0.0

    for (value in numbers) {
        sum += value
    }

    return sum / numbers.size
}

fun findAverageAndReplaceMutable(numbers: MutableList<Double>): Double {
    val average = findAverage(numbers)

    for (i in numbers.indices) {
        numbers[i] = numbers[i] - average
    }

    return average
}

fun findAverageAndReplaceMap(numbers: List<Double>): List<Double> {
    val average = findAverage(numbers)
    return numbers.map { it - average }
}

fun readIntValue(prompt: String): Int? {
    print(prompt)
    return readlnOrNull()?.trim()?.toIntOrNull()
}

fun readDoubleValue(prompt: String): Double? {
    print(prompt)
    val input = readlnOrNull()?.trim()?.replace(',', '.') ?: return null
    return input.toDoubleOrNull()
}

fun readArray(size: Int): MutableList<Double>? {
    val numbers = mutableListOf<Double>()

    for (i in 1..size) {
        val value = readDoubleValue("Введите элемент $i: ") ?: return null
        numbers.add(value)
    }

    return numbers
}

fun main() {
    println("Лабораторная работа 3")
    println("Задача: найти среднее значение массива и заменить каждый элемент разностью его исходного и среднего значений")
    println()

    val n = readIntValue("Введите количество элементов массива: ")

    if (n == null) {
        println("Ошибка: количество элементов должно быть целым числом")
        return
    }

    if (n <= 0) {
        println("Ошибка: количество элементов массива должно быть больше 0")
        return
    }

    val original = readArray(n)

    if (original == null) {
        println("Ошибка: элементы массива должны быть числами")
        return
    }

    val mutableVersion = original.toMutableList()
    val average1 = findAverageAndReplaceMutable(mutableVersion)

    val immutableVersion = original.toList()
    val average2 = findAverage(immutableVersion)
    val mappedVersion = findAverageAndReplaceMap(immutableVersion)

    println()
    println("Исходный массив: ${original.joinToString(prefix = "[", postfix = "]")}")

    println()
    println("Вариант 1: MutableList + for")
    println("Среднее значение = $average1")
    println("Результат = ${mutableVersion.joinToString(prefix = "[", postfix = "]")}")

    println()
    println("Вариант 2: List + map")
    println("Среднее значение = $average2")
    println("Результат = ${mappedVersion.joinToString(prefix = "[", postfix = "]")}")
}
