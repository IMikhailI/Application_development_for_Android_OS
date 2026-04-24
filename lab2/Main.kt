package org.example.lab2

import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.sin

const val EPS = 1e-9

fun findMinFunctionWhile(a: Double, b: Double, h: Double) {
    if (abs(h) < EPS) {
        println("while:")
        println("Ошибка: шаг не может быть равен 0")
        return
    }

    if ((b - a) / h < 0) {
        println("while:")
        println("Ошибка: шаг не подходит для данного интервала")
        return
    }

    var x = a
    var minX = 0.0
    var minY = 0.0
    var found = false

    if (h > 0) {
        while (x <= b + EPS) {
            if (abs(x) > EPS) {
                val y = 3 * sin(2 / x)

                if (!found || y < minY) {
                    minY = y
                    minX = x
                    found = true
                }
            }
            x += h
        }
    } else {
        while (x >= b - EPS) {
            if (abs(x) > EPS) {
                val y = 3 * sin(2 / x)

                if (!found || y < minY) {
                    minY = y
                    minX = x
                    found = true
                }
            }
            x += h
        }
    }

    println("while:")
    if (found) {
        println("min y = $minY")
        println("x = $minX")
    } else {
        println("На заданном наборе точек нет допустимых значений x")
    }
}

fun findMinFunctionFor(a: Double, b: Double, h: Double) {
    if (abs(h) < EPS) {
        println("for:")
        println("Ошибка: шаг не может быть равен 0")
        return
    }

    val stepsValue = (b - a) / h
    if (stepsValue < 0) {
        println("for:")
        println("Ошибка: шаг не подходит для данного интервала")
        return
    }

    val steps = floor(stepsValue + EPS).toInt()

    var minX = 0.0
    var minY = 0.0
    var found = false

    for (i in 0..steps) {
        val x = a + i * h

        if (abs(x) > EPS) {
            val y = 3 * sin(2 / x)

            if (!found || y < minY) {
                minY = y
                minX = x
                found = true
            }
        }
    }

    println("for:")
    if (found) {
        println("min y = $minY")
        println("x = $minX")
    } else {
        println("На заданном наборе точек нет допустимых значений x")
    }
}

fun readDoubleValue(prompt: String): Double? {
    print(prompt)
    return readlnOrNull()?.replace(',', '.')?.toDoubleOrNull()
}

fun main() {
    println("Лабораторная работа 2")
    println("Найти минимальное значение функции y = 3*sin(2/x) на заданном интервале")
    println()

    val a = readDoubleValue("Введите начало интервала a: ")
    val b = readDoubleValue("Введите конец интервала b: ")
    val h = readDoubleValue("Введите шаг h: ")

    if (a == null || b == null || h == null) {
        println("Ошибка: границы интервала и шаг должны быть числами")
        return
    }

    println()
    findMinFunctionWhile(a, b, h)
    println()
    findMinFunctionFor(a, b, h)
}
