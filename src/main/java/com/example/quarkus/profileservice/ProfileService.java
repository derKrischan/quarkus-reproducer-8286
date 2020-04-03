package com.example.quarkus.profileservice;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.quarkus.profileservice.model.Profile;

@ApplicationScoped
public class ProfileService {

	private static final Logger log = LoggerFactory.getLogger(ProfileService.class);
	
	@ConfigProperty(name = "reproducer.profileName")
	String profileName;

	/** just for tests */
	protected ProfileService( String profileName) {
		this.profileName = profileName;
	}
	
	public ProfileService() {
	}

	public Optional<Profile> findByName(String name) {
		log.info("ProfileService lookup for " + name  + " (correct is " + profileName + ")");
        if (name != null && name.equals(profileName)) {
        	return Optional.of(Profile.builder()
        			.name(name)
        			.loginName("sub-" + profileName)
        			.build());
        }
        return Optional.empty();
    }
}
