package ru.dekrement.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dekrement.model.User;

/**
 * Created by web on 07.03.2017.
 */
public class UserFormValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;
    }
}
