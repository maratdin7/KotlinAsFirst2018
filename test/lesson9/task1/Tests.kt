package lesson9.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
	@Test
	@Tag("Easy")
	fun createMatrix() {
		val matrix = createMatrix(4, 6, 0.0)
		assertEquals(4, matrix.height)
		assertEquals(6, matrix.width)
		val matrix1 = createMatrix(3, 2, 0)
		assertEquals(3, matrix1.height)
		assertEquals(2, matrix1.width)
		assertEquals(0, matrix1[0, 0])
	}

	@Test
	@Tag("Normal")
	fun getSetInt() {
		val matrix = createMatrix(3, 2, 0)
		var value = 0
		for (row in 0 until matrix.height) {
			for (column in 0 until matrix.width) {
				matrix[row, column] = value++
			}
		}
		value = 0
		for (row in 0 until matrix.height) {
			for (column in 0 until matrix.width) {
				assertEquals(value++, matrix[Cell(row, column)])
			}
		}
	}

	@Test
	@Tag("Normal")
	fun getSetString() {
		val matrix = createMatrix(2, 2, "")
		val strings = listOf("alpha", "beta", "gamma", "omega")
		var index = 0
		for (row in 0 until matrix.height) {
			for (column in 0 until matrix.width) {
				matrix[Cell(row, column)] = strings[index++]
			}
		}
		index = 0
		for (row in 0 until matrix.height) {
			for (column in 0 until matrix.width) {
				assertEquals(strings[index++], matrix[row, column])
			}
		}
	}

	@Test
	fun equals() {
        assertEquals(false, createMatrix(4, 6, 0.0) == createMatrix(4, 6, 1.0))
    }
}