package ru.danilov.onlineshop1.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.danilov.onlineshop1.exception.UserNotCreatedException;

import java.util.List;

public class ErrorsUtil {
    public static void returnErrors(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        throw new UserNotCreatedException(errorMsg.toString());
    }
}