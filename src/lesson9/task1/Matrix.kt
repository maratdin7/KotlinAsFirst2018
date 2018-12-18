@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
	/** Высота */
	val height: Int

	/** Ширина */
	val width: Int

	/**
	 * Доступ к ячейке.
	 * Методы могут бросить исключение, если ячейка не существует или пуста
	 */
	operator fun get(row: Int, column: Int): E

	operator fun get(cell: Cell): E

	/**
	 * Запись в ячейку.
	 * Методы могут бросить исключение, если ячейка не существует
	 */
	operator fun set(row: Int, column: Int, value: E)

	operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
	if (height <= 0 || width <= 0) throw IllegalArgumentException()
	return MatrixImpl(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
	private val matrix = mutableListOf<MutableList<E>>()
	init {
		for (i in 0 until height) {
			matrix.add(mutableListOf())

			for (j in 0 until width) {
				matrix[i].add(e)
			}
		}
	}

	override fun get(row: Int, column: Int): E = matrix[row][column]

	override fun get(cell: Cell): E = matrix[cell.row][cell.column]

	override fun set(row: Int, column: Int, value: E) {
		if (row < height && column < width) {
			 matrix[row][column] = value
		}
		else throw StackOverflowError()
	}

	override fun set(cell: Cell, value: E) {
		if (cell.row < height && cell.column < width) matrix[cell.row][cell.column] = value
		else throw StackOverflowError()
	}

	override fun equals(other: Any?) =
			other is MatrixImpl<*> &&
					height == other.height &&
					width == other.width && equalsElement(other)

	private fun equalsElement(other: Any?): Boolean = if (other is MatrixImpl<*>) {
		var a = true
		for (i in 0 until height) {
			for (j in 0 until width) {
				if (this[i, j] != other[i, j]) a = false
			}
		}
		a
	} else false

	override fun toString(): String {
		val sb = StringBuilder()
		sb.append("[")
		for (row in 0 until height) {
			sb.append("[")
			for (column in 0 until width) {
				sb.append(this[row, column])
			}
			if (row < height - 1) sb.append("], ")
			else sb.append("] ")
		}

		sb.append("]")
		return "$sb"
	}

	override fun hashCode(): Int {
		var result = height
		result = 31 * result + width
		result = 31 * result + matrix.hashCode()
		return result
	}

}

