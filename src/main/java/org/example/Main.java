package org.example;

import static org.example.Tasks.checkNumber;

public class Main {
    public static void main(String[] args) {
        Tasks.printThreeWorlds();//1

        Tasks.checkSumSign();//2

        Tasks.printColor();//3

        Tasks.compareNumbers();//4

        System.out.println(Tasks.checkSumInRange(3, 5)); //5
        System.out.println(Tasks.checkSumInRange(7, 5)); //5
        System.out.println(Tasks.checkSumInRange(333, 5)); //5

        checkNumber(-5);//6

        System.out.println(Tasks.negativeNumber(-8)); //7

        Tasks.printString("YHoo", 4);       //8

        System.out.println(Tasks.leapYear(2024)); //9

        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};//10
        Tasks.invertArray(arr);

        int[] arr1 = new int[100];//11
        Tasks.fillArray(arr1);

        int[] arr2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};//12
        Tasks.multiplyNum(arr2);

        Tasks.fillDiagonal(5);//13

        Tasks.printArray(Tasks.createArray(10, 55));//14
    }


}
