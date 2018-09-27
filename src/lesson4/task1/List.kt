@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
		when {
			y < 0 -> listOf()
			y == 0.0 -> listOf(0.0)
			else -> {
				val root = sqrt(y)
				// Результат!
				listOf(-root, root)
			}
		}

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
	if (a == 0.0) {
		return if (b == 0.0) listOf()
		else sqRoots(-c / b)
	}
	val d = discriminant(a, b, c)
	if (d < 0.0) return listOf()
	if (d == 0.0) return sqRoots(-b / (2 * a))
	val y1 = (-b + sqrt(d)) / (2 * a)
	val y2 = (-b - sqrt(d)) / (2 * a)
	return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
	val result = mutableListOf<Int>()
	for (element in list) {
		if (element < 0) {
			result.add(element)
		}
	}
	return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
	for (i in 0 until list.size) {
		val element = list[i]
		if (element > 0) {
			list[i] = -element
		}
	}
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
	val lowerCase = str.toLowerCase().filter { it != ' ' }
	for (i in 0..lowerCase.length / 2) {
		if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
	}
	return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double =
		sqrt(v.map({ sqr(it) }).sum()) //map преобразует элементы списка. возращает корень из сумм всех эл. list

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
		if (list.isNotEmpty()) list.sum() / list.size
		else 0.0

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
	val average = mean(list)
	for (i in 0 until list.size) {
		list[i] -= average
	}
	return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double = TODO()

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double = TODO()

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> = TODO()

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
	var numb = n
	var digit = mutableListOf<Int>()
	do {
		digit.add(numb % base)
		numb /= base
	} while (numb != 0)
	return digit.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
	var numb = n
	var ans = ""
	val digit = convert(n, base)
	val alp = alphabet(base)
	for (i in digit) ans += alp[i]
	return ans
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
	var ans = 0
	var powBase = 1
	for (i in digits.size - 1 downTo 0) {
		ans += digits[i] * powBase
		powBase *= base
	}
	return ans
}


/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
	val alp = alphabet(base)
	val normalNumb = normalNumber(str, alp)
	return decimal(normalNumb, base)
}

// составляем алфавит для системы счисления с осн base
fun alphabet(base: Int): MutableList<Char> {
	var alp = mutableListOf<Char>()
	var char: Char
	for (i in 0 until base) {
		if (i < 10) char = (48 + i).toChar()
		else char = (87 + i).toChar() // тк 97 код "а", и 10 итераций мы уже прошли
		alp.add(char)
	}
	return alp
}

// переводим цифры к 10тичным числам
fun normalNumber(str: String, list: MutableList<Char>): MutableList<Int> {
	var norm = mutableListOf<Int>()
	for (i in str) norm.add(list.indexOf(i))
	return norm
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun test(n: Int): String {
	for (i in 1..999999) {
		println(russian(i))
	}
	return russian(n)
}

fun russian(n: Int): String {
	val digitsNumber = digitOfNumber(n)

	while (digitsNumber.size < 6) digitsNumber.add(0)

	var numbRus = hundredInRussian(digitsNumber[5], digitsNumber[4], digitsNumber[3])

	if (numbRus.isNotEmpty()) numbRus = thousand(numbRus, digitsNumber[4], digitsNumber[3])
	numbRus += hundredInRussian(digitsNumber[2], digitsNumber[1], digitsNumber[0])

	return space(numbRus)
}


// для цифр
fun digitInRussian(digit: Int): String =
		when (digit) {
			1 -> " один"
			2 -> " два"
			3 -> " три"
			4 -> " четыре"
			5 -> " пять"
			6 -> " шесть"
			7 -> " семь"
			8 -> " восемь"
			9 -> " девять"
			else -> ""
		}

fun dickerInRussian(dicker: Int, digit: Int): String {
	val dig = digitInRussian(digit)
	val d = digitInRussian(dicker)
	return when {
		dicker == 1 && digit != 0 -> when {
			digit >= 4 -> dig.substring(0, dig.length - 1) + "надцать"
			digit == 2 -> " двенадцать"
			digit < 4 -> dig + "надцать"
			else -> ""
		}
		dicker == 0 -> dig
		dicker == 1 -> " десять"
		dicker <= 3 -> d + "дцать" + dig
		dicker == 4 -> " сорок" + dig
		dicker == 9 -> " девяносто" + dig
		dicker > 4 -> d + "десят" + dig
		else -> ""
	}
}

fun hundredInRussian(hundred: Int, dicker: Int, digit: Int): String {
	val hund = digitInRussian(hundred)
	val doub = dickerInRussian(dicker, digit)
	return when {
		hundred == 0 -> doub
		hundred == 1 -> " сто" + doub
		hundred == 2 -> " двести" + doub
		hundred <= 4 -> hund + "ста" + doub
		hundred > 4 -> hund + "сот" + doub
		else -> ""
	}
}
//конец обработки 3_значных чисел

//для добавления тысяч
fun thousand(str: String, dicker: Int, digit: Int): String =
		when {
			dicker == 1 || digit > 4 || digit == 0 -> str + " тысяч"
			digit == 1 -> str.substringBeforeLast(' ') + " одна тысяча"
			digit == 2 -> str.substringBeforeLast(' ') + " две тысячи"
			digit <= 4 -> str + " тысячи"
			else -> ""
		}


//разбиение числа на цифры и заполнение массива
fun digitOfNumber(n: Int): MutableList<Int> {
	var numb = n
	val list = mutableListOf<Int>()
	while (numb != 0) {
		list.add(numb % 10)
		numb /= 10
	}
	return list
}

// удаление лишних пробелов
fun space(s: String): String {
	var str = s
	var length = str.length
	val list = (1 until length).filterNot() { str[it] == ' ' && str[it - 1] == ' ' }
	return removeAt(list, str).trim()
}

fun removeAt(list: List<Int>, str: String): String {
	var s: String = "" + str[0]
	for (i in list) {
		s += str[i]
	}
	return s
}