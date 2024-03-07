package ru.danilov.onlineshop1.model;

import com.fasterxml.jackson.annotation.JsonView;

@JsonView
public enum Status {
    PROCESSING, SENT, RECEIVED
}