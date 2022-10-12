package com.drone.management.exceptions;

import com.drone.management.response.ApiResponse;
import com.drone.management.schedulingtasks.BatteryAudits;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;


@RestControllerAdvice
public class ApiExceptionHandler {

    private static Logger logger = LogManager.getLogger(ApiExceptionHandler.class);


    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse accessDeniedExceptionHandler(AccessDeniedException ex) {
        logger.error(ex);
        return new ApiResponse(403, HttpStatus.FORBIDDEN.toString(), "common.security.forbidden", ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse genericExceptionHandler(Exception ex) {
        logger.error(ex);
        if (ex instanceof MethodArgumentNotValidException) {
            StringBuilder errors = new StringBuilder();
            for (ObjectError error : ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors()) {
                errors.append(error.getDefaultMessage() + "   AND   ");
            }
            return new ApiResponse(500, HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error, internal server error", errors);
        } else {
            return new ApiResponse(500, HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error, internal server error", ex.getMessage());
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse badRequestExceptionHandler(ConstraintViolationException ex) {
        logger.error(ex);
        return new ApiResponse(400, HttpStatus.BAD_REQUEST.toString(), "Error, Bad Request", ex.getMessage());
    }
}
