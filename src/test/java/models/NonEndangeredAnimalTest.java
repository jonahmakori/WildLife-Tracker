package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class NonEndangeredAnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    private NonEndangeredAnimal testNonEndangeredAnimal;
    private NonEndangeredAnimal testNonEndangeredAnimals;

//    @Before
//    public void setup() {
//        testNonEndangeredAnimal = new NonEndangeredAnimal("Lion");
//        testEndangeredAnimals = new EndangeredAnimals("Tiger", "Healthy", "Young");
//
//    }

    @Test
    public void animal_instantiatesCorrectly_true() {
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Monkey");
        assertEquals(true, testNonEndangeredAnimal instanceof NonEndangeredAnimal);
    }

    @Test
    public void getName_animalInstantiatesWithName() {
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Lion");
        assertEquals("Lion", testNonEndangeredAnimal.getName());
    }

    @Test
    public void getId_animalInstantiatesWithId(){
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Lion");
        testNonEndangeredAnimal.save();
        assertTrue(testNonEndangeredAnimal.getId() > 0);
    }
    @Test
    public void getType_animalInstantiatesWithType_Type() {
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Lion");
        assertEquals("Non-endangered", testNonEndangeredAnimal.getType());
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame() {
        NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
        NonEndangeredAnimal anotherAnimal = new NonEndangeredAnimal("Deer");
        assertTrue(firstNonEndangeredAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_assignsIdToObject() {
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Monkey");
        testNonEndangeredAnimal.save();
        NonEndangeredAnimal savedNonEndangeredAnimal = NonEndangeredAnimal.all().get(0);
        assertEquals(testNonEndangeredAnimal.getId(), savedNonEndangeredAnimal.getId());
    }

    @Test
    public void save_insertsObjectIntoDatabase() {
        NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Lion");
        testNonEndangeredAnimal.save();
        assertTrue(NonEndangeredAnimal.all().get(0).equals(testNonEndangeredAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfAnimal() {
        NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Lion");
        firstNonEndangeredAnimal.save();
        NonEndangeredAnimal otherNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
        otherNonEndangeredAnimal.save();
        assertEquals(true, NonEndangeredAnimal.all().get(0).equals(firstNonEndangeredAnimal));
        assertEquals(true, NonEndangeredAnimal.all().get(1).equals(otherNonEndangeredAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId() {
        NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Lion");
        firstNonEndangeredAnimal.save();
        NonEndangeredAnimal anotherNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
        anotherNonEndangeredAnimal.save();
        assertEquals(NonEndangeredAnimal.find(anotherNonEndangeredAnimal.getId()), anotherNonEndangeredAnimal);
    }



//    @Test
//    public void getSightings_retrievesAllSightingsFromDatabase_sightingsList() {
//        Sighting testSighting = new Sighting(1,"Zone B","Jonah");
//        testSighting.save();
//        NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Monkey");
//        firstNonEndangeredAnimal.save();
//        NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("Lion");
//        secondNonEndangeredAnimal.save();
//        NonEndangeredAnimal[] nonEndangeredAnimal = new NonEndangeredAnimal[] { firstNonEndangeredAnimal, secondNonEndangeredAnimal };
//        assertTrue(testSighting.getNonEndangeredAnimals().containsAll(Arrays.asList(nonEndangeredAnimal)));
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
