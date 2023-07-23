package com.word.count.exceptions;

import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.ValidationException;
import org.jboss.resteasy.reactive.RestResponse;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = factory.createObjectBuilder()
                .add("errorMessage", exception.getLocalizedMessage()).build();
        Response.ResponseBuilder builder = Response.status(RestResponse.Status.BAD_REQUEST).entity(value);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build();
    }
}
