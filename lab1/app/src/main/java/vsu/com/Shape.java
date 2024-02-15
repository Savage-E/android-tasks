package vsu.com;

public abstract class Shape {

    private final String color;

    public Shape(String color) {
        super();
        this.color = color;
    }

    public final String getColor() {
        return this.color;
    }

    public abstract double area();

    public abstract double perimeter();
}
