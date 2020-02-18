package models;
import org.sql2o.Connection;

import java.util.List;

public class NonEndangeredAnimal extends Animal {

    public static final String ANIMAL_TYPE = "Non-endangered";

    public NonEndangeredAnimal(String name){
        if (name.equals("")){
            throw new IllegalArgumentException("Please enter an animal name.");
        }
        this.name = name;
        type = ANIMAL_TYPE;
    }

    @Override
    public boolean equals(Object otherNonEndangeredAnimal) {
        if (!(otherNonEndangeredAnimal instanceof NonEndangeredAnimal)) {
            return false;
        }else{
            NonEndangeredAnimal newNonEndangeredAnimal = (NonEndangeredAnimal) otherNonEndangeredAnimal;
            return this.getName().equals(newNonEndangeredAnimal.getName());
        }
    }

    public static List<NonEndangeredAnimal> all() {
        String sql = "SELECT * FROM animals WHERE type='Non-endangered';";

        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(NonEndangeredAnimal.class);
        }
    }

@Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type) VALUES (:name, :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static NonEndangeredAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id = :id;";
            NonEndangeredAnimal nonEndangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(NonEndangeredAnimal.class);
            return nonEndangeredAnimal;
        }
    }
    public void update() {
        String sql = "UPDATE animals SET name = :name WHERE id = :id";

        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }

}
