package com.au.igreendata.exception;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RecordNotFoundAdvice {

    Logger log = getLogger(RecordNotFoundAdvice.class);

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> serviceExceptionHandler(final ServiceException ex) {
        return error(NOT_FOUND, ex);
    }

    private ResponseEntity<String> error(final HttpStatus status, final Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status)
                             .body(e.getMessage());
    }
}