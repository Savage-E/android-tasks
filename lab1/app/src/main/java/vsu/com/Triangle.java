package vsu.com;

public class Triangle extends Shape {
    private final double sideOne;
    private final double sideTwo;
    private final double sideThree;

    public Triangle(String color, double sideOne, double sideTwo, double sideThree) {
        super(color);
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        this.sideThree = sideThree;
    }

    public final double getSideOne() {
        return this.sideOne;
    }

    public final double getSideTwo() {
        return this.sideTwo;
    }

    public final double getSideThree() {
        return this.sideThree;
    }

    public double area() {
        double s = (this.sideOne + this.sideTwo + this.sideThree) / (double) 2;
        return Math.sqrt(s * (s - this.sideOne) * (s - this.sideTwo) * (s - this.sideThree));
    }

    public double perimeter() {
        return this.sideOne + this.sideTwo + this.sideThree;
    }
}
