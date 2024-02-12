package vsu.com

fun calculateSumArea(shapes: List<Shape>): Double {
    var totalArea = 0.0
    for (shape in shapes) {
        totalArea += shape.area()
    }
    return totalArea
}