@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
	/**
	 * Пример
	 *
	 * Рассчитать (по известной формуле) расстояние между двумя точками
	 */
	fun distance(other: Point): Double = sqrt(sqr(x - other.x) + sqr(y - other.y))

	fun center(b: Point): Point = Point((x + b.x) / 2, (y + b.y) / 2)
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
class Triangle private constructor(private val points: Set<Point>) {

	private val pointList = points.toList()

	val a: Point get() = pointList[0]

	val b: Point get() = pointList[1]

	val c: Point get() = pointList[2]

	constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))


	fun distAb(): Double = a.distance(b)

	fun distAc(): Double = a.distance(c)

	fun distBc(): Double = b.distance(c)

	fun flatAngle(): Double {
		val ab2 = sqr(distAb())
		val ac2 = sqr(distAc())
		val bc2 = sqr(distBc())
		return (ab2 + ac2 - bc2) / (2 * distAc() * distAb())
	}

	/**
	 * Пример: полупериметр
	 */
	fun halfPerimeter() = (distAb() + distBc() + distAc()) / 2.0

	/**
	 * Пример: площадь
	 */
	fun area(): Double {
		val p = halfPerimeter()
		return sqrt(p * (p - distAb()) * (p - distBc()) * (p - distAc()))
	}

	/**
	 * Пример: треугольник содержит точку
	 */
	fun contains(p: Point): Boolean {
		val abp = Triangle(a, b, p)
		val bcp = Triangle(b, c, p)
		val cap = Triangle(c, a, p)
		return abp.area() + bcp.area() + cap.area() <= area()
	}

	override fun equals(other: Any?) = other is Triangle && points == other.points

	override fun hashCode() = points.hashCode()

	override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
	/**
	 * Простая
	 *
	 * Рассчитать расстояние между двумя окружностями.
	 * Расстояние между непересекающимися окружностями рассчитывается как
	 * расстояние между их центрами минус сумма их радиусов.
	 * Расстояние между пересекающимися окружностями считать равным 0.0.
	 */
	fun distance(other: Circle): Double = max(0.0, center.distance(other.center) - (radius + other.radius))

	/**
	 * Тривиальная
	 *
	 * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
	 */
	fun contains(p: Point): Boolean = center.distance(p) <= radius
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
	override fun equals(other: Any?) =
			other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

	override fun hashCode() =
			begin.hashCode() + end.hashCode()
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
	val temp = Point(0.0, 0.0)
	var ans = Segment(temp, temp)
	var maxi = -1.0
	if (points.size < 2) throw IllegalArgumentException()
	for ((i, p) in points.withIndex()) {
		for (j in (i + 1) until points.size) {
			val dist = p.distance(points[j])
			if (maxi < dist) {
				maxi = dist
				ans = Segment(p, points[j])
			}
		}
	}
	return ans
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
	val center = diameter.begin.center(diameter.end)
	val radious = center.distance(diameter.begin)
	return Circle(center, radious)
}

/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line private constructor(val b: Double, val angle: Double) {
	init {
		require(angle >= 0 && angle < PI) { "Incorrect line angle: $angle" }
	}

	constructor(point: Point, angle: Double) : this(point.y * cos(angle) - point.x * sin(angle), angle)

	/**
	 * Средняя
	 *
	 * Найти точку пересечения с другой линией.
	 * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
	 */
	fun crossPoint(other: Line): Point {
		val xCross = (other.b * cos(angle) - b * cos(other.angle)) / sin(angle - other.angle)

		return if (abs(cos(angle)) > abs(cos(other.angle)))
			Point(xCross, this.yCross(xCross))
		else {
			Point(xCross, other.yCross(xCross))
		}
	}

	private fun yCross(xCross: Double): Double = (xCross * sin(this.angle) + this.b) / cos(this.angle)

	override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

	override fun hashCode(): Int {
		var result = b.hashCode()
		result = 31 * result + angle.hashCode()
		return result
	}

	override fun toString() = "Line(${cos(angle)} * y = ${sin(angle)} * x + $b)"
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line = TODO()

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line {
	 if ((b.x - a.x) == 0.0) return Line(a, PI / 2)
	val k = (b.y - a.y) / (b.x - a.x)
	val angle = atan(k)
	return if (angle < 0.0) Line(a, PI + angle)
	else Line(a, angle)
}
/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
	var angle = lineByPoints(a, b).angle
	if (angle < PI / 2) angle += PI / 2
	else angle -= PI / 2
	return Line(a.center(b), angle)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> = TODO()

/**
 * Сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
	val triangle = Triangle(a, b, c)
	val s = triangle.area()
	val radius = (triangle.distAb() * triangle.distAc() * triangle.distBc()) / (4 * s)
	val cerPerAb = bisectorByPoints(a, b)
	val cerPerBc = bisectorByPoints(b, c)
	val center = cerPerAb.crossPoint(cerPerBc)
	return Circle(center, radius)
}

/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */
fun minContainingCircle(vararg points: Point): Circle = TODO()

