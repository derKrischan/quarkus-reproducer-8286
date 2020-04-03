package com.example.quarkus.profileservice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.quarkus.jwt.TokenUtils;
import com.example.quarkus.profileservice.model.Profile;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@ExtendWith(MockitoExtension.class)
public class ProfilesControllerTest {

	@Mock
    ProfileService profileService;

//	ProfileService profileService = Mockito.mock(ProfileService.class);

    /**
     * The test generated JWT token string
     */
    private String token;

    @BeforeEach
    public void generateToken() throws Exception {
//    	MockitoAnnotations.initMocks(this);
    	profileService = Mockito.mock(ProfileService.class);
    	Map<String, Long> claims = new HashMap<>();
        token = TokenUtils.generateTokenString("/JwtClaims.json", claims);
    }
    
    @Test
    public void givenCallToProfilesForName_returnsProfileData() {
    	profileService = Mockito.mock(ProfileService.class);
    	Profile expectedProfile = Profile.builder()
    			.name("Clyde")
    			.loginName("sub-Clyde")
    			.build();
    	when(profileService.findByName(anyString())).thenReturn(Optional.of(expectedProfile));
    	Profile result = given().auth().oauth2(token)
        .when().get("/profiles/Clyde")
          .then()
             .statusCode(200)
             .contentType(is("application/json"))
          .extract().as(Profile.class);
    	assertEquals(expectedProfile, result);
    }
    
}
