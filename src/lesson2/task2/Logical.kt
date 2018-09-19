@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import lesson2.task1.rookOrBishopThreatens
import kotlin.math.sqrt
import kotlin.math.truncate

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
		sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
	val firstHf = sumTwoDigit(number / 100)
	val secondHf = sumTwoDigit(number % 100)
	return firstHf == secondHf
}

// сумма двух цифр
fun sumTwoDigit(twoDigits: Int): Int =
		twoDigits % 10 + twoDigits / 10

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
		rookOrBishopThreatens(x1, y1, x2, y2, x2, y2) != 0


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int = when {
	(month != 2) ->
		when {
			(month in 1..7 && month % 2 == 1) -> 31
			(month in 8..12 && month % 2 == 0) -> 31
			else -> 30
		}
	(leapYear(year)) -> 29
	else -> 28
}

fun leapYear(year: Int): Boolean =
		((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
				 x2: Double, y2: Double, r2: Double): Boolean =
		sqrt(sqr(x1 - x2) + sqr(y1 - y2)) + r1 <= r2


/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
	val ab = isItInserted(a, b, r, s)
	val ac = isItInserted(a, c, r, s)
	val cb = isItInserted(c, b, r, s)
	return (ab || ac || cb)
}

//Проверка размеров кирпича
fun isItInserted(a: Int, b: Int, r: Int, s: Int): Boolean = when {
	(a <= r && b <= s) -> true
	(a <= s && b <= r) -> true
	else -> false
}