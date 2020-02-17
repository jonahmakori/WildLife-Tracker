package models;
import org.sql2o.Connection;

import java.util.List;

public class NonEndangeredAnimal extends Animal {

    public static final String ANIMAL_TYPE = "Non-endangered";

    public NonEndangeredAnimal(String name){
        if (name.equals("")){
            //throw exception if no name is entered
            throw new IllegalArgumentException("Please enter an animal name.");
        }
        this.name = name;
//        this.added = false;
        type = ANIMAL_TYPE;
    }


    // overriding Animal
    @Override
    public boolean equals(Object otherNonEndangeredAnimal) {
        if (!(otherNonEndangeredAnimal instanceof NonEndangeredAnimal)) {
            return false;
        }else{
            NonEndangeredAnimal newNonEndangeredAnimal = (NonEndangeredAnimal) otherNonEndangeredAnimal;
            return this.getName().equals(newNonEndangeredAnimal.getName());
        }
    }



    //Listing all animals from animals table
    public static List<NonEndangeredAnimal> all() {
        String sql = "SELECT * FROM animals WHERE type='Non-endangered';";

        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(NonEndangeredAnimal.class);
//                    .throwOnMappingFailure(false)

        }
    }

    //finding an animal using its id && throwing  exception incase the id is not mapped
    public static NonEndangeredAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id = :id;";
            NonEndangeredAnimal nonEndangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
//                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(NonEndangeredAnimal.class);
            return nonEndangeredAnimal;
        }
    }

    //updating an animal using its Id && throwing an exception incase it is not mapped
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
