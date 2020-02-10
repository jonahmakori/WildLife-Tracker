package models;
import org.junit.After;
import org.junit.Test;
import org.junit.*;
import org.sql2o.*;
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
    public void SaveInsertObjectAnimalIntoDatabase(){
        Animal testAnimal = new Animal("Lion");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }
}