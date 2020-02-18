package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimals extends Animal {
    private String age;
    private String health;
    public static final String ANIMAL_TYPE = "Endangered";

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
    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public void setAge(String age) {
        this.age = age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndangeredAnimals)) return false;
        EndangeredAnimals that = (EndangeredAnimals) o;
        return getAge().equals(that.getAge()) &&
                getHealth().equals(that.getHealth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getHealth());
    }

    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type, health, age ) VALUES (:name, :type, :health, :age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<EndangeredAnimals> all() {
        String sql = "SELECT * FROM animals WHERE type='Endangered';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimals.class);
        }
    }
    public static EndangeredAnimals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimals.class);
        }
    }
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
