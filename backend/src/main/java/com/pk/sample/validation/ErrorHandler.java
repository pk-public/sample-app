package com.pk.sample.validation;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@ResponseBody
public class ErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = NOT_FOUND)
    public List<String> handleValidationError(ValidationException e) {
        return e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getCode)
                .distinct()
                .collect(toList());
    }
}
