package vsu.com;

import org.junit.Assert;
import org.junit.Test;

public class CircleTest {

    @Test
    public void testCircleArea() {
        Circle circle = new Circle("White", 2.0);
        Assert.assertEquals(12.56, circle.area(), 0.01);
    }

    @Test
    public void testCirclePerimeter() {
        Circle circle = new Circle("Beige", 2.0);
        Assert.assertEquals(12.56, circle.perimeter(), 0.01);
    }
}