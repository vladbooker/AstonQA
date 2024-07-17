package org.example;

import org.example.Animal;

class Dog extends Animal {
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }
    @Override
    void run(int length) {
        if (length <= 500) {
            System.out.println(name + " пробежал " + length + " м.");
        } else {
            System.out.println(name + " может пробежать максимум 500 м.");
        }
    }
    @Override
    void swim(int length) {
        if (length <= 10) {
            System.out.println(name + " проплыл " + length + " м.");
        } else {
            System.out.println(name + " может проплыть максимум 10 м.");
        }
    }
    public static int getDogCount() {
        return dogCount;
    }
}