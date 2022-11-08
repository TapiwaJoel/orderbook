package za.co.varl.orderbook.utils.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.co.varl.orderbook.utils.dtos.ServiceResponse;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations().iterator().next().getMessage();
        log.info("Exception: {}", errorMessage);
        ServiceResponse<Object> objectServiceResponse = ServiceResponse.builder().message(errorMessage).build();
        log.info("objectServiceResponse: {}", objectServiceResponse);
        return new ResponseEntity<>(objectServiceResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceConflictException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleResourceConflictException(
            RuntimeException e) {
        String errorMessage = e.getMessage();
        log.info("Exception: {}", errorMessage);
        ServiceResponse<Object> objectServiceResponse = ServiceResponse.builder().message(errorMessage).build();
        log.info("objectServiceResponse: {}", objectServiceResponse);
        return new ResponseEntity<>(objectServiceResponse, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<String> handleConflict(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleBadRequestException(
            RuntimeException e) {
        String errorMessage = e.getMessage();
        log.info("Exception: {}", errorMessage);
        ServiceResponse<Object> objectServiceResponse = ServiceResponse.builder().message(errorMessage).build();
        log.info("objectServiceResponse: {}", objectServiceResponse);
        return new ResponseEntity<>(objectServiceResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleRuntimeException(
            RuntimeException e) {
        String errorMessage = e.getMessage();
        log.info("Exception: {}", errorMessage);
        ServiceResponse<Object> objectServiceResponse = ServiceResponse.builder().message(errorMessage).build();
        log.info("objectServiceResponse: {}", objectServiceResponse);
        return new ResponseEntity<>(objectServiceResponse, HttpStatus.BAD_REQUEST);
    }
}
