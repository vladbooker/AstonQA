package org.example;

public class Park {

    private String name;
    private Attraction attraction;

    public Park(String name, String attractionName, String workingHours, double cost) {
        this.name = name;
        this.attraction = new Attraction(attractionName, workingHours, cost);
    }

    public void displayAttractionInfo() {
        System.out.println("Парк: " + name + "\n" +
                "Информация об аттракционе:" + "\n" +
                "Название: " + attraction.getName() + "\n" +
                "Время работы: " + attraction.getWorkingHours() + "\n" +
                "Стоимость: " + attraction.getCost());
    }

    public class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public String getWorkingHours() {
            return workingHours;
        }

        public double getCost() {
            return cost;
        }
    }
}
