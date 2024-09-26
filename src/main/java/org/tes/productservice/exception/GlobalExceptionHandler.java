package org.tes.productservice.exception;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse createErrorResponse(
            HttpStatus status,
            Exception exception,
            WebRequest request
    ) {
        log.error(
                exception.getClass().getSimpleName()
                        + " occurred: "
                        + exception.getMessage()
        );

        return new ErrorResponse(
                status.value(),
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleModelNotFoundException(
            ModelNotFoundException exception,
            WebRequest request
    ) {
        return createErrorResponse(
                HttpStatus.NOT_FOUND,
                exception,
                request
        );
    }

    @ExceptionHandler(ModelIsNullException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleModelIsNullException(
            ModelIsNullException exception,
            WebRequest request
    ) {
        return createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception,
                request
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGlobalException(
            Exception exception,
            WebRequest request
    ) {
        return createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception,
                request
        );
    }
}
