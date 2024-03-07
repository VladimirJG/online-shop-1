package ru.danilov.onlineshop1.exception;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException implements Supplier<String> {
    public UserNotFoundException() {
        System.out.println("User not found by this id");
    }

    @Override
    public String get() {
        return "User not found by this id";
    }
}