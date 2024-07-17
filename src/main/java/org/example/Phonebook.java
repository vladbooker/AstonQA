package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phonebook {
    private Map<String, List<String>> phoneBook = new HashMap<>();

    public void add(String name, String number) {
        List<String> numbers = phoneBook.getOrDefault(name, new ArrayList<>());
        numbers.add(number);
        phoneBook.put(name, numbers);
    }

    public List<String> get(String name) {
        return phoneBook.getOrDefault(name, new ArrayList<>());
    }
}
