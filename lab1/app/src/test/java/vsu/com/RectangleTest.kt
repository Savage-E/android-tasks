package vsu.com

import org.junit.Assert
import org.junit.Test

class RectangleTest {
    @Test
    fun testRectangleArea() {
        val rectangle = Rectangle("Black", 3.0, 5.0)
        Assert.assertEquals(15.0, rectangle.area(), 0.01)
    }
    @Test
    fun testRectanglePerimeter() {
        val rectangle = Rectangle("Black", 3.0, 5.0)
        Assert.assertEquals(16.0, rectangle.perimeter(), 0.01)
    }
}