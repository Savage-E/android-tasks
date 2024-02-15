package vsu.com;

import java.util.List;

public final class Utils {
    public static double calculateSumArea(List<Shape> shapes) {
        double totalArea = 0.0;

        for (int i = 0; i < shapes.size(); i++) {
            totalArea += shapes.get(i).area();
        }

        return totalArea;
    }
}