package com.bhhan.springbootmvc.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by hbh5274@gmail.com on 2020-01-26
 * Github : http://github.com/bhhan5274
 */

public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;
        if(event.getName().equalsIgnoreCase("aaa")){
            errors.rejectValue("name", "wrongValue", "the value is not allowed");
        }
    }
}
