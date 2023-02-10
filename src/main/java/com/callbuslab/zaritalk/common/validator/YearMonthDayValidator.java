package com.callbuslab.zaritalk.common.validator;

import com.callbuslab.zaritalk.base.anotation.YearMonthDay;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthDayValidator implements ConstraintValidator<YearMonthDay, String> {

    private String pattern;

    @Override
    public void initialize(YearMonthDay constraintAnnotation) {
        constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // default: yyyyMMdd
        try {
            LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(this.pattern));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
