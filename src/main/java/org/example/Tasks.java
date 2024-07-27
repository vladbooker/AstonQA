package org.example;

public class Tasks {

    public static void printThreeWorlds() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = 200;
        int b = -203;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 99;
        if (value < 0) {
            System.out.println("Красный");
        } else if (value < 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = 5;
        int b = 10;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean checkSumInRange(int a, int b) {//5
        return ((a + b) > 10 && (a + b) <= 20);
    }

    public static void checkNumber(int number) {
        String message = (number >= 0) ? "положительное" : "отрицательное";
        System.out.println("Число " + number + " " + message);
    }

    public static boolean negativeNumber(int a) { //7
        return a < 0;
    }

    public static void printString(String str, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(str);
        }
    }
    public static boolean leapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }


    public static void invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else if (arr[i] == 1) {
                arr[i] = 0;
            }
        }
        for (int j : arr) {
            System.out.println(j + " ");
        }
    }

    public static void fillArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
    public static void multiplyNum(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void fillDiagonal(int size) {
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[size - 1 - i][i] = 1;;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] createArray(int len, int initialValue) {//14
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}


