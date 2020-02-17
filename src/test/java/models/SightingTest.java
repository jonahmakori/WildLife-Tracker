package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SightingTest{
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Sighting_instantiatesCorrectly() {
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        assertEquals(true,testSighting instanceof Sighting);
    }

    @Test
    public void getId_sightingInstantiatesWithId(){
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        testSighting.save();
        assertTrue(testSighting.getId() > 0);
    }

    @Test
    public void getLocation_sightingInstantiatesWithLocation(){
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        testSighting.save();
        assertEquals("zone A", testSighting.getLocation());
    }

    @Test
    public void getRangerName_sightingInstantiatesWithRangerName(){
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        testSighting.save();
        assertEquals("Justus", testSighting.getRangerName());
    }
    @Test
    public void equals_returnsTrueIfAllPropertiesAreTheSame() {
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        Sighting anotherSighting = new Sighting(  1,"zone A", "Justus");
        assertTrue(testSighting.equals(anotherSighting));
    }

    @Test
    public void save_assignsIdToObject() {
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        testSighting.save();
        Sighting savedSighting = Sighting.getAll().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());
    }

    @Test
    public void save_insertsObjectIntoDatabase() {
        Sighting testSighting = new Sighting(1,"zoneA","Justus");
        testSighting.save();
        assertEquals(Sighting.getAll().get(0), testSighting);
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        testSighting.save();
        Sighting otherSighting = new Sighting(  1,"Zone B",  "Baraka");
        otherSighting.save();
        assertEquals(true, Sighting.getAll().get(0).equals(testSighting));
        assertEquals(true, Sighting.getAll().get(1).equals(otherSighting));
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        testSighting.save();
        Sighting otherSighting = new Sighting(  1,"Zone B",  "Daniels");
        otherSighting.save();

        assertEquals(Sighting.find(otherSighting.getId()), otherSighting);
    }
    @Test
    public void save_savesAnimalIdIntoDB_true() {
        Sighting testSighting = new Sighting( 1,"Zone A","Justus");
        testSighting.save();
        assertTrue(Sighting.getAll().get(0).equals(testSighting));
    }


    @Test
    public void save_savesEndangeredAnimalIdIntoDB_true() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Lion","Ill","Old");
        testEndangeredAnimals.save();
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        testSighting.save();
        Sighting savedSighting = Sighting.find(testSighting.getId());
        assertEquals(savedSighting.getId(), testEndangeredAnimals.getId());
    }



    @Test
    public void delete_deletesSighting() {
        Sighting testSighting = new Sighting(1,"zone A","Justus");
        testSighting.save();
        testSighting.delete();
        assertEquals(0, Sighting.getAll().size());
    }

}
