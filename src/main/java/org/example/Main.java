package org.example;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        employees[0] = new Employee("Ivanov Ivan", "Engineer", "ivan@mailbox.com", "+375 29 999 99 99", 3000, 30);
        employees[1] = new Employee("Petrov Petr", "Manager", "petr@mailbox.com", "+375 29 888 88 88", 4000, 35);
        employees[2] = new Employee("Sidorov Alexandr", "QA", "alex@mailbox.com", "+375 29 777 77 77", 2500, 25);
        employees[3] = new Employee("Smirnova Maria", "HR Specialist", "maria@mailbox.com", "+375 29 666 66 66", 3500, 28);
        employees[4] = new Employee("Kuznetsova Irina", "Developer", "irina@mailbox.com", "+375 29 555 55 55", 5000, 32);

        for (Employee employee : employees) {
            employee.printInfo();
        }


        Park park;
        park = new Park("Развлекательный парк", "Колесо обозрения", "с 10:00 до 22:00", 500.0);
        park.displayAttractionInfo();
    }
}