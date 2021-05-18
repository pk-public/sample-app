package com.pk.sample.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.ObjectError;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationException extends RuntimeException {

    private final List<ObjectError> allErrors;

}
