package org.example;

class Plate {
    private int food;
    public Plate(int food) {
        this.food = food;
    }
    public boolean decreaseFood(int amount) {
        if (food >= amount) {
            food -= amount;
            return true;
        }
        return false;
    }
    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
        }
    }
}
