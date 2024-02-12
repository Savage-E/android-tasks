package vsu.com

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun testСaclulateSumArea() {
        val circle = Circle("Red", 2.0)
        val rectangle = Rectangle("Blue", 3.0, 5.0)
        val triangle = Triangle("White", 4.0, 4.0, 6.0)

        val shapes = listOf(circle, rectangle, triangle)

        assertEquals(35.50, calculateSumArea(shapes), 0.01)
    }
}