package org.example.lab4

import kotlin.math.abs

interface ConsoleIO {
    fun readFromConsole()
    fun printToConsole()
}

interface Attackable {
    fun attacks(targetX: Int, targetY: Int): Boolean
}

interface PieceOperations {
    fun sortByRank(pieces: List<ChessPiece>): List<ChessPiece>
    fun findByColor(pieces: List<ChessPiece>, color: PieceColor): List<ChessPiece>
    fun findAttacking(pieces: List<ChessPiece>, targetX: Int, targetY: Int): List<ChessPiece>
    fun findNotAttacking(pieces: List<ChessPiece>, targetX: Int, targetY: Int): List<ChessPiece>
}

enum class PieceColor(val title: String) {
    WHITE("белый"),
    BLACK("черный");

    companion object {
        fun fromString(value: String): PieceColor? {
            return when (value.trim().lowercase()) {
                "white", "белый", "белая", "w" -> WHITE
                "black", "черный", "чёрный", "черная", "чёрная", "b" -> BLACK
                else -> null
            }
        }
    }
}

abstract class ChessPiece(
    var x: Int = 1,
    var y: Int = 1,
    var color: PieceColor = PieceColor.WHITE
) : ConsoleIO, Attackable {

    abstract val name: String
    abstract val rank: Int

    override fun readFromConsole() {
        x = readIntInRange("Введите координату x (1..8): ", 1, 8)
        y = readIntInRange("Введите координату y (1..8): ", 1, 8)
        color = readColor("Введите цвет фигуры (white/black, белый/черный): ")
    }

    override fun printToConsole() {
        println("$name: координаты=($x, $y), цвет=${color.title}, ранг=$rank")
    }
}

class Pawn : ChessPiece() {
    override val name = "Пешка"
    override val rank = 1

    override fun attacks(targetX: Int, targetY: Int): Boolean {
        return when (color) {
            PieceColor.WHITE -> targetY == y + 1 && (targetX == x - 1 || targetX == x + 1)
            PieceColor.BLACK -> targetY == y - 1 && (targetX == x - 1 || targetX == x + 1)
        }
    }
}

class Knight : ChessPiece() {
    override val name = "Конь"
    override val rank = 2

    override fun attacks(targetX: Int, targetY: Int): Boolean {
        val dx = abs(targetX - x)
        val dy = abs(targetY - y)
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2)
    }
}

class Bishop : ChessPiece() {
    override val name = "Слон"
    override val rank = 3

    override fun attacks(targetX: Int, targetY: Int): Boolean {
        val dx = abs(targetX - x)
        val dy = abs(targetY - y)
        return dx == dy && dx != 0
    }
}

class Rook : ChessPiece() {
    override val name = "Ладья"
    override val rank = 4

    override fun attacks(targetX: Int, targetY: Int): Boolean {
        return (targetX == x || targetY == y) && !(targetX == x && targetY == y)
    }
}

class Queen : ChessPiece() {
    override val name = "Ферзь"
    override val rank = 5

    override fun attacks(targetX: Int, targetY: Int): Boolean {
        val dx = abs(targetX - x)
        val dy = abs(targetY - y)
        return ((targetX == x || targetY == y) || dx == dy) && !(targetX == x && targetY == y)
    }
}

class King : ChessPiece() {
    override val name = "Король"
    override val rank = 6

    override fun attacks(targetX: Int, targetY: Int): Boolean {
        val dx = abs(targetX - x)
        val dy = abs(targetY - y)
        return dx <= 1 && dy <= 1 && !(dx == 0 && dy == 0)
    }
}

class ChessPieceService : PieceOperations {
    override fun sortByRank(pieces: List<ChessPiece>): List<ChessPiece> = pieces.sortedBy { it.rank }

    override fun findByColor(pieces: List<ChessPiece>, color: PieceColor): List<ChessPiece> =
        pieces.filter { it.color == color }

    override fun findAttacking(pieces: List<ChessPiece>, targetX: Int, targetY: Int): List<ChessPiece> =
        pieces.filter { it.attacks(targetX, targetY) }

    override fun findNotAttacking(pieces: List<ChessPiece>, targetX: Int, targetY: Int): List<ChessPiece> =
        pieces.filter { !it.attacks(targetX, targetY) }
}

fun readInt(prompt: String): Int? {
    print(prompt)
    return readlnOrNull()?.trim()?.toIntOrNull()
}

fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        val value = readInt(prompt)
        if (value != null && value in min..max) {
            return value
        }
        println("Ошибка: введите целое число в диапазоне от $min до $max")
    }
}

fun readColor(prompt: String): PieceColor {
    while (true) {
        print(prompt)
        val value = readlnOrNull()?.trim().orEmpty()
        val color = PieceColor.fromString(value)
        if (color != null) {
            return color
        }
        println("Ошибка: введите white/black или белый/черный")
    }
}

fun createPieceByType(type: String): ChessPiece? {
    return when (type.trim().lowercase()) {
        "pawn", "пешка" -> Pawn()
        "knight", "конь" -> Knight()
        "bishop", "слон" -> Bishop()
        "rook", "ладья" -> Rook()
        "queen", "ферзь" -> Queen()
        "king", "король" -> King()
        else -> null
    }
}

fun readPieceType(): ChessPiece {
    while (true) {
        print("Введите тип фигуры (pawn/knight/bishop/rook/queen/king или по-русски): ")
        val type = readlnOrNull()?.trim().orEmpty()
        val piece = createPieceByType(type)
        if (piece != null) {
            return piece
        }
        println("Ошибка: неизвестный тип фигуры")
    }
}

fun printPieces(title: String, pieces: List<ChessPiece>) {
    println(title)
    if (pieces.isEmpty()) {
        println("Список пуст")
    } else {
        pieces.forEach { it.printToConsole() }
    }
    println()
}

fun main() {
    val count = readIntInRange("Введите количество фигур: ", 1, Int.MAX_VALUE)

    val pieces = mutableListOf<ChessPiece>()
    for (i in 1..count) {
        println("\nФигура №$i")
        val piece = readPieceType()
        piece.readFromConsole()
        pieces.add(piece)
    }

    val service = ChessPieceService()

    println("\nИсходный список фигур:")
    pieces.forEach { it.printToConsole() }

    val sortedPieces = service.sortByRank(pieces)
    printPieces("\nФигуры, отсортированные по рангу:", sortedPieces)

    val colorForSearch = readColor("Введите цвет для поиска фигур: ")
    val piecesByColor = service.findByColor(pieces, colorForSearch)
    printPieces("\nФигуры цвета ${colorForSearch.title}:", piecesByColor)

    val targetX = readIntInRange("Введите координату x клетки (1..8): ", 1, 8)
    val targetY = readIntInRange("Введите координату y клетки (1..8): ", 1, 8)

    val attacking = service.findAttacking(pieces, targetX, targetY)
    val notAttacking = service.findNotAttacking(pieces, targetX, targetY)

    printPieces("\nФигуры, которые бьют клетку ($targetX, $targetY):", attacking)
    printPieces("Фигуры, которые не бьют клетку ($targetX, $targetY):", notAttacking)

}
