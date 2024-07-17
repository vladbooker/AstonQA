package org.example;

public interface Shape {
    double getPerimeter();
    double getArea();
    String getFillColor();
    String getBorderColor();

    default double calculatePerimeter(double[] sides) {
        double perimeter = 0;
        for (double side : sides) {
            perimeter += side;
        }
        return perimeter;
    }
}