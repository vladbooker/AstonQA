package org.example;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5.0, "Красный", "Чёрный");
        Rectangle rectangle = new Rectangle(4.0, 6.0, "Синий", "Зеленый");
        Triangle triangle = new Triangle(3.0, 4.0, 5.0, "Жёлтый", "Розовый");

        System.out.println("Круг: Периметр = " + circle.getPerimeter() +
                ", Площадь = " + circle.getArea() +
                ", Цвет фона = " + circle.getFillColor() +
                ", Цвет границы = " + circle.getBorderColor());

        System.out.println("Прямоугольник: Периметр = " + rectangle.getPerimeter() +
                ", Площадь = " + rectangle.getArea() +
                ", Цвет фона = " + rectangle.getFillColor() +
                ", Цвет границы = " + rectangle.getBorderColor());

        System.out.println("Треугольник: Периметр = " + triangle.getPerimeter() +
                ", Площадь = " + triangle.getArea() +
                ", Цвет фона = " + triangle.getFillColor() +
                ", Цвет границы = " + triangle.getBorderColor());


        Dog dogSharik = new Dog("Шарик");
        Cat catBarsik = new Cat("Барсик");

        dogSharik.run(500);
        dogSharik.swim(10);

        catBarsik.run(200);
        catBarsik.swim(0);

        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Леопольд");
        Dog dog1 = new Dog("Бобик");

        System.out.println("Количество созданных котов: " + Cat.getCatCount() + "\n" +
                "Количество созданных собак: " + Dog.getDogCount() + " \n" +
                "Общее количество животных: " + Animal.getAnimalCount());

        Plate plate = new Plate(20);
        Cat [] cats = {catBarsik, cat1, cat2};
        for (Cat c : cats) {
            c.eat(plate);
            System.out.println(c.getName() + " сытость: " + c.isSatiated());
        }

        int additionalFood = 10;
        plate.addFood(additionalFood);
        System.out.println("Добавили в миску еды: " + additionalFood);

    }
}