package org.example;

abstract class Animal {
    private static int animalCount = 0;
    protected String name;
    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    abstract void run(int length);
    abstract void swim(int length);

    public static int getAnimalCount() {
        return animalCount;
    }
    public String getName() {
        return name;
    }
}
