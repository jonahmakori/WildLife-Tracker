package models;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EndangeredAnimalsTest {
    private EndangeredAnimals testEndangeredAnimals;
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Before
    public void setup() {
        testEndangeredAnimals = new EndangeredAnimals("Tiger", "Healthy", "Young");
    }

    @Test
    public void animal_instantiatesCorrectly_true() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        assertEquals(true, testEndangeredAnimals instanceof EndangeredAnimals);
    }

    @Test
    public void getName_animalInstantiatesWithName() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        assertEquals("Monkey", testEndangeredAnimals.getName());
    }

    @Test
    public void getId_animalInstantiatesWithId(){
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        testEndangeredAnimals.save();
        assertTrue(testEndangeredAnimals.getId() > 0);
    }
    @Test
    public void getType_animalInstantiatesWithType_Type() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        assertEquals("Endangered", testEndangeredAnimals.getType());
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame() {
        EndangeredAnimals firstEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        EndangeredAnimals anotherEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        assertTrue(firstEndangeredAnimals.equals(anotherEndangeredAnimals));
    }

    @Test
    public void save_assignsIdToObject() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        testEndangeredAnimals.save();
        EndangeredAnimals savedEndangeredAnimals = EndangeredAnimals.all().get(0);
        assertEquals(testEndangeredAnimals.getId(), savedEndangeredAnimals.getId());
    }

    @Test
    public void save_insertsObjectIntoDatabase() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        testEndangeredAnimals.save();
        assertEquals(EndangeredAnimals.all().get(0), testEndangeredAnimals);
    }

    @Test
    public void all_returnsAllInstancesOfAnimal() {
        EndangeredAnimals firstEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        firstEndangeredAnimals.save();
        EndangeredAnimals otherEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        otherEndangeredAnimals.save();
        assertEquals(true, EndangeredAnimals.all().get(0).equals(firstEndangeredAnimals));
        assertEquals(true, EndangeredAnimals.all().get(1).equals(otherEndangeredAnimals));
    }

    @Test
    public void find_returnsAnimalWithSameId() {
        EndangeredAnimals firstEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        firstEndangeredAnimals.save();
        EndangeredAnimals anotherEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
        anotherEndangeredAnimals.save();
        assertEquals(firstEndangeredAnimals.find(anotherEndangeredAnimals.getId()), firstEndangeredAnimals);
    }



//    @Test
//    public void getSightings_retrievesAllSightingsFromDatabase_sightingsList() {
//        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Monkey","Ill","young");
//        testEndangeredAnimals.save();
//        Sighting firstSighting = new Sighting(1,"Zone B","Jonah");
//        firstSighting.save();
//        Sighting secondSighting = new Sighting(1,"Zone C","Evans");
//        secondSighting.save();
//        Sighting[] sightings = new Sighting[] { firstSighting, secondSighting };
//        assertTrue(testEndangeredAnimals.getSightings().containsAll(Arrays.asList(sightings)));
//    }






}
//    @Test
//    public void delete_deletesAnimal() {
//        Animal testAnimal = new Animal("Lion");
//        testAnimal.save();
//        testAnimal.delete();
//        assertEquals(0, Animal.all().size());
//    }
//}
