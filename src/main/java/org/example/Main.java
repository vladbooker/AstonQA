package org.example;

public class Main {
    public static int calculateFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
    public static void main(String[] args) {
        int number = 5;
        System.out.println("Факториал числа " + number + " число: " + calculateFactorial(number));
    }
}
