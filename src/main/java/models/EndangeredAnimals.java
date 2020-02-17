package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;
// endangered animal extends animal but with new attributes age and health

public class EndangeredAnimals extends Animal {
    public String name;
    public String health;
    public String age;
    public int id;
    public static final String ANIMAL_TYPE = "EndangeredAnimals";

    // constructor with animal attributes and new attributes for endangered animal
    public EndangeredAnimals(String name, String health, String age) {
        if (name.equals("") || health.equals("") || age.equals("")){
            throw new IllegalArgumentException("Please enter all input fields.");
        }
        this.health = health;
        this.age = age;
        this.name = name;
        this.id = id;
        type = ANIMAL_TYPE;
    }

    //get methods for endangered animal
    public String getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
    public String getAge() {
        return age;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    //set method for endangered animals age
    public void setAge(String age) {
        this.age = age;
    }

    // overriding endangered animal

//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
       if(o == null || getClass() != o.getClass()) return false;
        EndangeredAnimals that = (EndangeredAnimals) o;
        return id == that.id &&
                name.equals(that.name) &&
                health.equals(that.health) &&
                age.equals(that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, age, id);
    }




//    @Override
//    public boolean equals(Object otherEndangeredAnimals) {
//        if (otherEndangeredAnimals instanceof EndangeredAnimals) {
//            EndangeredAnimals newEndangeredAnimals = (EndangeredAnimals) otherEndangeredAnimals;
//            return (this.getName().equals(newEndangeredAnimals.getName()));
//        }
//
//        return false;
//    }







    public static List<EndangeredAnimals> all() {
        String sql = "SELECT * FROM animals WHERE type='EndangeredAnimals';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimals.class);
        }
    }




    // finding endangered animal with a static type that will apply to animal class too
    public static EndangeredAnimals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimals.class);
        }
    }

    //Overriding update method from Animal class for endangered animal
    @Override
    public void update() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET name = :name, health = :health, age = :age WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("health", health)
                    .addParameter("age", age)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
