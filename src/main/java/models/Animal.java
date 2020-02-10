package models;

import java.util.ArrayList;

public class Animal {
    private String name;
    private int id;
    private boolean created = false;
    private boolean added;
    private static ArrayList<Animal> numberOfAnimals = new ArrayList<>();

    public Animal(String name) {
        this.added = false;
        this.id = numberOfAnimals.size();
        this.name = name;
    }


    public static void clearAllAnimals() {
        numberOfAnimals.clear();
    }
//
//    public boolean isCreated() {
//        return created;
//    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }
    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Animal)){
            return false;
        }else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }
}
