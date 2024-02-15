package vsu.com;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {
    @Test
    public void testTriangleArea() {
        Triangle triangle = new Triangle("Green", 4.0, 4.0, 6.0);
        Assert.assertEquals(7.93, triangle.area(), 0.01);
    }

    @Test
    public void testTrianglePerimeter() {
        Triangle triangle = new Triangle("Green", 4.0, 4.0, 6.0);
        Assert.assertEquals(14.0, triangle.perimeter(), 0.01);
    }
}