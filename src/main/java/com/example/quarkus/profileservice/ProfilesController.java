package com.example.quarkus.profileservice;

import java.util.Arrays;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.ExceptionMapper;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.quarkus.profileservice.model.Profile;

import io.netty.handler.codec.http.HttpMethod;


@Path("/profiles")
public class ProfilesController {
    
	private static final Logger log = LoggerFactory.getLogger(ProfilesController.class);

	@Inject
    JsonWebToken jwt; 
	
    ProfileService profileService;

    @Inject
    ProfilesController(ProfileService profileService) {
    	this.profileService = profileService;
    }
    
    @GET
    @Path("/{name}")
    @Produces("application/json")
    public Profile getForName(@Context SecurityContext ctx, @PathParam String name) {
        log.info("Searching for " + name);
    	return profileService.findByName(name).orElseThrow(() -> new NotFoundException());
    }
    
    @GET
    @Path("/resp/{name}")
    @Produces("application/json")
    public Response getForNameWithResponse(@Context SecurityContext ctx, @PathParam String name) {
    	log.info("Searching for " + name);
    	Profile profile = profileService.findByName(name).orElseThrow(() -> new NotFoundException());
    	return Response.ok().header(HttpHeaders.ALLOW, Arrays.asList(HttpMethod.GET.name(), HttpMethod.OPTIONS.name())).entity(profile).build();
    }

    
    static class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    	
    	private static final Logger log = LoggerFactory.getLogger(NotFoundExceptionMapper.class);
    	
    	@Override
    	public Response toResponse(NotFoundException notFoundException) {
    		log.info("Not found exception catched and handled with 404", notFoundException);
    		return Response.status(Status.NOT_FOUND).build();
    	}
    }
}
