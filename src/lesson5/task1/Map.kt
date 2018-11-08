@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import java.lang.Math.max

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
		shoppingList: List<String>,
		costs: Map<String, Double>): Double {
	var totalCost = 0.0

	for (item in shoppingList) {
		val itemCost = costs[item]
		if (itemCost != null) {
			totalCost += itemCost
		}
	}

	return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
		phoneBook: MutableMap<String, String>,
		countryCode: String) {
	val namesToRemove = mutableListOf<String>()

	for ((name, phone) in phoneBook) {
		if (!phone.startsWith(countryCode)) {
			namesToRemove.add(name)
		}
	}

	for (name in namesToRemove) {
		phoneBook.remove(name)
	}
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
		text: List<String>,
		vararg fillerWords: String): List<String> {
	val fillerWordSet = setOf(*fillerWords)

	val res = mutableListOf<String>()
	for (word in text) {
		if (word !in fillerWordSet) {
			res += word
		}
	}
	return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
	val res = mutableSetOf<String>()
	for (word in text) res.add(word)
	return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
	val mapC = mutableMapOf<String, String>()
	mapC.putAll(mapA)
	for ((i, value) in mapB) {
		val def = i
		if (def in mapC && mapC[def] != value)
			mapC[def] = mapC[def] + ", " + value
		//когда было написано 	mapC[def] += ", "+value программа бросала исключение. Хотелось бы узнать почему?
		else
			mapC.put(i, value)
	}
	return mapC
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
	val map = mutableMapOf<Int, List<String>>()
	for (i in grades) {
		map[i.value] = map.getOrDefault(i.value, listOf()) + i.key
	}
	for (i in map) map[i.key] = i.value.sortedDescending()
	return map
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean = TODO()

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
	val sale = mutableMapOf<String, Double>()
	stockPrices.forEach { sale[it.first] = (sale.getOrDefault(it.first, it.second) + it.second) / 2 }
	return sale
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? = TODO()

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
	val ans = mutableMapOf<String, Set<String>>()
	val names = mutableSetOf<String>()
	for ((name, friend) in friends) { //чтобы найти все имена
		names.addAll(friend)
		names.add(name)
	}
	for (name in names) {
		if (friends[name] == null) {
			ans[name] = setOf()
		} else ans[name] = personFriends(friends, name)
	}
	return ans
}

fun personFriends(friends: Map<String, Set<String>>, name: String): Set<String> {
	val faceControl = mutableSetOf(name)
	val myFriends = mutableSetOf<String>()
	return whoseHands(friends, faceControl, myFriends, friends[name]!!)
}

fun whoseHands(
		friends: Map<String, Set<String>>,
		faceControl: MutableSet<String>,
		myFriends: MutableSet<String>,
		hisFriends: Set<String>): MutableSet<String> {

	for (nameMyFriends in hisFriends) {

		if (nameMyFriends !in faceControl) {
			faceControl.add(nameMyFriends)
			myFriends.add(nameMyFriends)

			if (friends[nameMyFriends] !== null) {
				myFriends.addAll(
						whoseHands(
								friends,
								faceControl,
								myFriends,
								friends[nameMyFriends]!!
						)
				)
			}
		}
	}
	return myFriends
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit = TODO()

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = TODO()

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
	val letter = mutableMapOf<Char, Boolean>()
	word.forEach { letter[it] = false }
	chars.forEach {
		if (letter[it] == false) letter[it] = true
	}
	return letter.all { it.value == true }
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> = TODO()

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
	val anagramm = mutableSetOf<List<Char>>()
	for (i in words) {
		val word = sorted(i)
		if (word in anagramm) return true
		else anagramm.add(word)
	}
	return false
}

fun sorted(str: String): List<Char> = str.toList().sorted()
/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
	for (i in list) {
		val first = list.indexOf(i)
		val second = list.lastIndexOf(number - i)
		val set = setOf(first, second)
		if (set.size == 2 && (-1 !in set)) {
			return Pair(set.first(), set.last())
		}
	}
	return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
	val a = mutableListOf<Int>()
	for (i in 0..capacity) {
		a.add(0)
	}

	val rucksack: MutableList<MutableList<Int>> = mutableListOf(a)
	val name = mutableListOf<String>()

	treasures.forEach { name.add(it.key) }

	for (i in 1..treasures.size) {
		rucksack.add(mutableListOf(0))
		val jewel = treasures[name[i - 1]]!!

		for (j in 1..capacity) {
			when {
				(jewel.first > j) -> rucksack[i].add(rucksack[i - 1][j])
				else -> rucksack[i].add(max(rucksack[i - 1][j], rucksack[i - 1][j - jewel.first] + jewel.second))
			}
		}
	}
	var i = treasures.size
	var j = capacity
	val ans = mutableSetOf<String>()

	while (i != 0 && j != 0) {
		val jewel = treasures[name[i - 1]]!!

		if (jewel.first <= j && (rucksack[i - 1][j] <= (rucksack[i - 1][j - jewel.first] + jewel.second))) {
			ans.add(name[i - 1])
			j -= jewel.first
		}

		i--
	}
	return ans
}


