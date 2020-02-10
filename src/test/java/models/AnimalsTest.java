package models;


import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnimalsTest {

    @After
    void tearDown() {Animals.clearAllAnimals();
    }
    @Test
    public void TestThatInstanceOfAnimalIsCreated(){
        Animals testAnimals = new Animals("Lion");
        assertEquals(true,testAnimals instanceof Animals);
    }


}