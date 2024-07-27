package org.example;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private double salary;
    private int age;

    public Employee(String fullName, String position, String email, String phoneNumber, double salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void printInfo() {
        System.out.println("Информация о сотруднике:" +"\n" +
                "ФИО: " + fullName + "\n" +
                "Должность: " + position + " \n" +
                "Email: " + email + "\n" +
                "Телефон: " + phoneNumber + "\n" +
                "Зарплата: " + salary + "\n" +
                "Возраст: " + age);
    }
}
