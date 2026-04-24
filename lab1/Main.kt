package org.example.lab1

// Вспомогательные функции

fun cross(ax: Int, ay: Int, bx: Int, by: Int): Int = ax * by - ay * bx
fun dot(ax: Int, ay: Int, bx: Int, by: Int): Int = ax * bx + ay * by
fun len2(x: Int, y: Int): Int = x * x + y * y

fun quadrilateralArea2(
    x1: Int, y1: Int,
    x2: Int, y2: Int,
    x3: Int, y3: Int,
    x4: Int, y4: Int
): Int = x1 * y2 + x2 * y3 + x3 * y4 + x4 * y1 - y1 * x2 - y2 * x3 - y3 * x4 - y4 * x1

fun isValidQuadrilateral(
    x1: Int, y1: Int,
    x2: Int, y2: Int,
    x3: Int, y3: Int,
    x4: Int, y4: Int
): Boolean {
    val ab2 = len2(x2 - x1, y2 - y1)
    val bc2 = len2(x3 - x2, y3 - y2)
    val cd2 = len2(x4 - x3, y4 - y3)
    val da2 = len2(x1 - x4, y1 - y4)
    val area2 = quadrilateralArea2(x1, y1, x2, y2, x3, y3, x4, y4)

    return ab2 != 0 && bc2 != 0 && cd2 != 0 && da2 != 0 && area2 != 0
}

// Задача 1. if-вариант

fun parallelogramTypeIf(
    x1: Int, y1: Int,
    x2: Int, y2: Int,
    x3: Int, y3: Int,
    x4: Int, y4: Int
): String {
    val valid = isValidQuadrilateral(x1, y1, x2, y2, x3, y3, x4, y4)

    val abx = x2 - x1
    val aby = y2 - y1
    val bcx = x3 - x2
    val bcy = y3 - y2
    val cdx = x4 - x3
    val cdy = y4 - y3
    val dax = x1 - x4
    val day = y1 - y4

    val ab2 = len2(abx, aby)
    val bc2 = len2(bcx, bcy)

    val oppositeSidesParallel =
        cross(abx, aby, cdx, cdy) == 0 && cross(bcx, bcy, dax, day) == 0

    val isParallelogram = valid && oppositeSidesParallel
    val isRectangle = isParallelogram && dot(abx, aby, bcx, bcy) == 0
    val isRhombus = isParallelogram && ab2 == bc2

    if (!valid) {
        return "некорректные входные данные"
    } else if (isRectangle && isRhombus) {
        return "квадрат"
    } else if (isRectangle) {
        return "прямоугольник"
    } else if (isRhombus) {
        return "ромб"
    } else if (isParallelogram) {
        return "параллелограмм"
    }
    return "произвольный"
}

// Задача 1. when-вариант

fun parallelogramTypeWhen(
    x1: Int, y1: Int,
    x2: Int, y2: Int,
    x3: Int, y3: Int,
    x4: Int, y4: Int
): String {
    val valid = isValidQuadrilateral(x1, y1, x2, y2, x3, y3, x4, y4)

    val abx = x2 - x1
    val aby = y2 - y1
    val bcx = x3 - x2
    val bcy = y3 - y2
    val cdx = x4 - x3
    val cdy = y4 - y3
    val dax = x1 - x4
    val day = y1 - y4

    val ab2 = len2(abx, aby)
    val bc2 = len2(bcx, bcy)

    val oppositeSidesParallel =
        cross(abx, aby, cdx, cdy) == 0 && cross(bcx, bcy, dax, day) == 0

    val isParallelogram = valid && oppositeSidesParallel
    val isRectangle = isParallelogram && dot(abx, aby, bcx, bcy) == 0
    val isRhombus = isParallelogram && ab2 == bc2

    return when {
        !valid -> "некорректные входные данные"
        isRectangle && isRhombus -> "квадрат"
        isRectangle -> "прямоугольник"
        isRhombus -> "ромб"
        isParallelogram -> "параллелограмм"
        else -> "произвольный"
    }
}

// Задача 2. if-вариант

fun figurePlacementIf(r: Int, d1: Int, d2: Int): String {
    val valid = r > 0 && d1 > 0 && d2 > 0

    val circleInRhombus =
        valid && 4 * r * r * (d1 * d1 + d2 * d2) <= d1 * d1 * d2 * d2

    val rhombusInCircle =
        valid && maxOf(d1, d2) <= 2 * r

    if (!valid) {
        return "некорректные входные данные"
    } else if (circleInRhombus) {
        return "окружность в ромбе"
    } else if (rhombusInCircle) {
        return "ромб в окружности"
    }
    return "ни одно размещение невозможно"
}

// Задача 2. when-вариант

fun figurePlacementWhen(r: Int, d1: Int, d2: Int): String {
    val valid = r > 0 && d1 > 0 && d2 > 0

    val circleInRhombus =
        valid && 4 * r * r * (d1 * d1 + d2 * d2) <= d1 * d1 * d2 * d2

    val rhombusInCircle =
        valid && maxOf(d1, d2) <= 2 * r

    return when {
        !valid -> "некорректные входные данные"
        circleInRhombus -> "окружность в ромбе"
        rhombusInCircle -> "ромб в окружности"
        else -> "ни одно размещение невозможно"
    }
}

fun readIntValue(prompt: String): Int? {
    print(prompt)
    return readlnOrNull()?.trim()?.toIntOrNull()
}

fun main() {
    println("Задача 1. Определение типа четырехугольника")
    println("Введите координаты вершин в порядке обхода: A -> B -> C -> D")

    val x1 = readIntValue("x1 = ")
    val y1 = readIntValue("y1 = ")
    val x2 = readIntValue("x2 = ")
    val y2 = readIntValue("y2 = ")
    val x3 = readIntValue("x3 = ")
    val y3 = readIntValue("y3 = ")
    val x4 = readIntValue("x4 = ")
    val y4 = readIntValue("y4 = ")

    if (x1 == null || y1 == null || x2 == null || y2 == null || x3 == null || y3 == null || x4 == null || y4 == null) {
        println("Ошибка: координаты должны быть целыми числами")
        return
    }

    println("Результат через if:   " + parallelogramTypeIf(x1, y1, x2, y2, x3, y3, x4, y4))
    println("Результат через when: " + parallelogramTypeWhen(x1, y1, x2, y2, x3, y3, x4, y4))

    println()
    println("Задача 2. Размещение окружности и ромба")

    val r = readIntValue("r = ")
    val d1 = readIntValue("d1 = ")
    val d2 = readIntValue("d2 = ")

    if (r == null || d1 == null || d2 == null) {
        println("Ошибка: радиус и диагонали должны быть целыми числами")
        return
    }

    println("Результат через if:   " + figurePlacementIf(r, d1, d2))
    println("Результат через when: " + figurePlacementWhen(r, d1, d2))
}