package org.example;

class Cat extends Animal {
    private static int catCount = 0;
    private boolean satiety;
    public Cat(String name) {
        super(name);
        catCount++;
        satiety = false;
    }
    @Override
    void run(int length) {
        if (length <= 200) {
            System.out.println(name + " пробежал " + length + " м.");
        } else {
            System.out.println(name + " может пробежать максимум 200 м.");
        }
    }
    @Override
    void swim(int length) {
        System.out.println(name + " не умеет плавать");
    }
    public static int getCatCount() {
        return catCount;
    }
    public void eat(Plate plate) {
        if (plate.decreaseFood(10)) {
            satiety = true;
        }
    }
    public boolean isSatiated() {
        return satiety;
    }
}
