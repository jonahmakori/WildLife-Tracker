package models;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    public int id;
    public String name;
    public String type;


    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }


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
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE from animals WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
            String sql2 = "DELETE from sightings WHERE animal_id = :id";
            con.createQuery(sql2)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }




    public List<Object> getSightings() {
        List<Object> allSightings = new ArrayList<Object>();

        try(Connection con = DB.sql2o.open()) {
            String sqlNonEndangeredAnimal = "SELECT * FROM sightings WHERE animal_Id=:id AND type='Non-endangered';";
            List<NonEndangeredAnimal> nonEndangeredAnimal = con.createQuery(sqlNonEndangeredAnimal)
                    .addParameter("id", this.id)
                    .executeAndFetch(NonEndangeredAnimal.class);
            allSightings.addAll(nonEndangeredAnimal);

            String sqlEndangeredAnimals = "SELECT * FROM sightings WHERE animal_Id=:id AND type='EndangeredAnimals';";
            List<EndangeredAnimals> EndangeredAnimals = con.createQuery(sqlEndangeredAnimals)
                    .addParameter("id", this.id)
                    .executeAndFetch(EndangeredAnimals.class);
            allSightings.addAll(EndangeredAnimals);
        }

        return allSightings;
    }


    public List<NonEndangeredAnimal> getNonEndangeredAnimal() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where nonEndangeredAnimalId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(NonEndangeredAnimal.class);
        }
    }


    public List<EndangeredAnimals> getEndangeredAnimals() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where EndangeredAnimalsId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(EndangeredAnimals.class);
        }
    }
    public abstract void update();

}