package com.example.quarkus.profileservice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfileServiceTest {


    private ProfileService profileServiceUnderTest;

    @BeforeEach
    public void setUp() throws Exception {
        profileServiceUnderTest = new ProfileService("Billy");
    }

    @Test
    public void givenCorrectName_whenFindByNameIsCalled_shouldReturnProfile() {
        assertTrue(profileServiceUnderTest.findByName("Billy").isPresent());
    }
    
    @Test
    public void givenWrongName_whenFindByNameIsCalled_shouldReturnEmpty() {
    	assertFalse(profileServiceUnderTest.findByName("Idol").isPresent());
    }


}
