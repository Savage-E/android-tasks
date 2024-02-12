package vsu.com;

import org.junit.Assert.*

import org.junit.Test;

class CircleTest {
    @Test
    fun testCircleArea() {
        val circle = Circle("White", 2.0)
        assertEquals(12.56, circle.area(), 0.01)
    }
    @Test
    fun testCirclePerimeter() {
        val circle = Circle("Beige", 2.0)
        assertEquals(12.56, circle.perimeter(), 0.01)
    }

}
