package com.mkyong.exceptions;

import com.mkyong.validation.ValidationError;
import org.apache.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;

/**
 * Created by D-QU92GL on 27/02/2018.
 */
@Provider
public class ConstraintExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(prepareMessage(exception.getConstraintViolations()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private ValidationError prepareMessage(Set<ConstraintViolation<?>> constraintViolations) {
        ValidationError error = new ValidationError("Validation failed. " + constraintViolations.size() + " error(s)");
        for (ConstraintViolation violation : constraintViolations) {
            error.addValidationError(violation.getPropertyPath() + " : "+ violation.getMessage());

        }
        return error;
    }
}

