package models;

import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


public class Sighting  {
    private int id ;
    private int animal_Id;
    private String location;
    private String ranger_name;
    private Timestamp view_time;

//     constructor for sighting which implements abstract method save in Database management class

    public Sighting(String location, String ranger_name,int animal_Id) {
        if (ranger_name.equals("")) {
            throw new IllegalArgumentException("Please enter Ranger name.");
        }
        this.location = location;
        this.ranger_name = ranger_name;
        this.animal_Id = animal_Id;
    }




    public Sighting(int animal_Id, String location, String ranger_name) {
        this.animal_Id = animal_Id;
        this.id = id;
        this.location = location;
        this.ranger_name = ranger_name;
    }

    public Sighting(int animal_Id, int i, String location, String ranger_name) {
    }

    public static Object all() {
        return all();
    }


    //get methods
    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return getId() == sighting.getId() &&
                Objects.equals(animal_Id, sighting.animal_Id) &&
                Objects.equals(location, sighting.location) &&
                Objects.equals(ranger_name, sighting.ranger_name) &&
                Objects.equals(getViewTime(), sighting.getViewTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), animal_Id, location, ranger_name, getViewTime());
    }
//    public int getEndangeredAnimalId() {
//        return endangeredAnimal_id;
//    }

    public String getLocation()
    {
        return location;
    }

    public String getRangerName()
    {
        return ranger_name;
    }

    public Timestamp getViewTime() {
        return view_time;
    }



    //set methods for Sightings
    public void setLocation(String location) {
        this.location = location;
    }

    public void setRangerName(String rangerName) {
        this.ranger_name = rangerName;
    }

    //Overriding save  method && implement method save() from Database management class


    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings(location, ranger_name, animal_Id) VALUES (:location, :ranger_name, :animal_Id)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("location", this.location)
                    .addParameter("ranger_name", this.ranger_name)
                    .addParameter("animal_Id",this.animal_Id)
//                    .addParameter("view_time",this.view_time)
                    .executeUpdate()
                    .getKey();
        }
    }

    //Listing all sightings from  sightings table
    public static List<Sighting> getAll() {
        String sql = "SELECT * FROM sightings";

        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Sighting.class);

        }
    }


    // finding a sighting using its id && with unchecked exception  that ensures index number entered by the user is within the range of the array.
    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
//         catch (IndexOutOfBoundsException exception) {
//            return null;
//        }
    }

    //implement method delete() from Database management class
    public void delete(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sightings WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

    //update the Sightings table && throwing an exception in case the id is not mapped
    public void update() {
        String sql = "UPDATE sightings SET location = :location, ranger_name = :ranger_name, animal_Id = :animal_Id, view_time = :view_time WHERE id = :id";

        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("location", location)
                    .addParameter("ranger_name", ranger_name)
                    .addParameter("animal_Id",this.animal_Id)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }

}