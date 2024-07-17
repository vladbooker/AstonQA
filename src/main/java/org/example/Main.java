package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] words = {
                "Volvo", "BMW", "Volvo",
                "Honda", "Kia", "BMW",
                "Saab", "Honda", "Audi", "Kia"};

        Map<String, Integer> wordCount = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            uniqueWords.add(word);
        }

        System.out.println("Список уникальных слов:");
        for (String uniqueWord : uniqueWords) {
            System.out.println(uniqueWord);
        }

        System.out.println("\nКоличество встреч каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Phonebook phoneBook = new Phonebook();
        phoneBook.add("Лебедев", "+375 29 111 11 11");
        phoneBook.add("Лобанов", "+375 29 333 33 33");
        phoneBook.add("Зайцев", "+375 29 555 55 55");

        System.out.println("\nТелефонный справочник:");
        System.out.println("Номер Лебедева: " + phoneBook.get("Лебедев") +
                "\n" + "Номер Лобанова: " + phoneBook.get("Лобанов") +
                "\n" + "Номер Зайцева: " + phoneBook.get("Зайцев"));
    }
}