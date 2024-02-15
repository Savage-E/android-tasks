package vsu.com;

public class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public double area() {
        return this.width * this.height;
    }

    public double perimeter() {
        return (double) 2 * (this.width + this.height);
    }
}
