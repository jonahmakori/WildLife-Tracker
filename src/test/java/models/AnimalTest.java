package models;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @After
    public void tearDown() {Animal.clearAllAnimals();
    }
    @Test
    public void TestThatInstanceOfAnimalIsCreated(){
        Animal testAnimal = new Animal("Lion");
        assertEquals(true,testAnimal instanceof Animal);
    }

    @Test
    public void TestThatAnimalInstantiatesWithName_Lion(){
        Animal testAnimal = new Animal("Lion");
        assertEquals("Lion",testAnimal.getName());
    }
    @Test
    public void EqualTestThatReturnsTrueIfNameAndAnotherAnimalsNameAreSame(){
        Animal firstAnimal = new Animal("Lion");
        Animal anotherAnimal = new Animal("Lion");
        assertTrue(firstAnimal.equals(anotherAnimal));

    }

    @Test
    public void TestSaveInsertObjectAnimalIntoDatabase(){
        Animal testAnimal = new Animal("Lion");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }
    @Test
    public void TestThatReturnsAllInstancesOfAnimal(){
        Animal firstAnimal = new Animal("Lion");
        firstAnimal.save();
        Animal secondAnimal = new Animal("Buffalo");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }
    @Test
    public void save_assignsIdToObject() {
        Animal testAnimal = new Animal("Lion");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = new Animal("Lion");
        firstAnimal.save();
        Animal secondAnimal = new Animal("Buffalo");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }

}