package com.example.quarkus.profileservice;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfilesControllerExceptionMapper implements ExceptionMapper<IllegalAccessException> {

	private static final Logger log = LoggerFactory.getLogger(ProfilesControllerExceptionMapper.class);

    @Override
    public Response toResponse(IllegalAccessException throwable) {
        log.error("Caught and handled in the Global Exception handler", throwable);
        return Response.status(Status.UNAUTHORIZED).build();
    }
}
