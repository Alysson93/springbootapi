package com.example.springbootapi.validations;

import java.util.List;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@SuppressWarnings("rawtypes")
public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty() ? true : false;        
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        
    }
    
}
