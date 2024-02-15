package vsu.com;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UtilsTest {

    @Test
    public void testCalculateSumArea() {
        Circle circle = new Circle("Red", 2.0);
        Rectangle rectangle = new Rectangle("Blue", 3.0, 5.0);
        Triangle triangle = new Triangle("White", 4.0, 4.0, 6.0);
        List<Shape> shapes = Arrays.asList(
                circle, rectangle, triangle);
        Assert.assertEquals(35.5, Utils.calculateSumArea(shapes), 0.01);
    }
}