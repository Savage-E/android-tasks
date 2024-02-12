package vsu.com

import kotlin.math.sqrt

abstract class Shape(val color: String) {
    abstract fun area(): Double
    abstract fun perimeter(): Double
}

class Circle(color: String, val radius: Double) : Shape(color) {
    override fun area(): Double = Math.PI * radius * radius

    override fun perimeter(): Double = 2 * Math.PI * radius
}

class Rectangle(color: String, val width: Double, val height: Double) : Shape(color) {
    override fun area(): Double = width * height

    override fun perimeter(): Double = 2 * (width + height)
}

class Triangle(color: String, val sideOne: Double, val sideTwo: Double, val sideThree: Double) :
    Shape(color) {
    override fun area(): Double {
        val s = (sideOne + sideTwo + sideThree) / 2
        return sqrt(s * (s - sideOne) * (s - sideTwo) * (s - sideThree))
    }

    override fun perimeter(): Double = sideOne + sideTwo + sideThree
}