package models;

import java.util.ArrayList;

public class Animals {
    private String name;
    private int id;
    private boolean created = false;
    private boolean added;
    private static ArrayList<Animals> numberOfAnimals = new ArrayList<>();

    public Animals(String name) {
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
}
